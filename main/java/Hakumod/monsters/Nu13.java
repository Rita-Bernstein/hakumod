package Hakumod.monsters;
import com.megacrit.cardcrawl.actions.AbstractGameAction.AttackEffect;
import com.megacrit.cardcrawl.actions.animations.TalkAction;
import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.actions.common.DamageAction;
import com.megacrit.cardcrawl.actions.common.GainBlockAction;
import com.megacrit.cardcrawl.actions.common.RollMoveAction;
import com.megacrit.cardcrawl.actions.utility.WaitAction;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.localization.MonsterStrings;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.powers.ArtifactPower;
import com.megacrit.cardcrawl.powers.BufferPower;
import com.megacrit.cardcrawl.powers.StrengthPower;
import com.megacrit.cardcrawl.powers.WeakPower;

import com.megacrit.cardcrawl.core.Settings;

import Hakumod.powers.Haku_DefensePower;
import Hakumod.powers.Haku_MagatamaPower;
import Hakumod.powers.Haku_UnlimitedDriveBossPower;

public class Nu13 extends AbstractMonster{

	public static final String ID = "Haku_Nu13";
	private static final MonsterStrings monsterStrings = CardCrawlGame.languagePack.getMonsterStrings(ID);
    
    public static final String NAME = monsterStrings.NAME;
    public static final String[] MOVES = monsterStrings.MOVES;
    public static final String[] DIALOG = monsterStrings.DIALOG;
    
    //Ascension 4: Boss are tougher
    //Ascension 9: Boss are deadlier
    //Ascension 19: Boss enemies have more challenging movesets and abilities. 
    
    public static final String MAP_IMAGE = "Hakumod/img/monster/map/Nu13_map.png";
	public static final String MAP_OUT = "Hakumod/img/monster/map/Nu13_mapOutline.png";
    private static final String IMAGE = "Hakumod/img/monster/Nu13.png";
    
    private static final int MAX_HEALTH = 200;
    private static final int ASC9_MAX_HEALTH = 220;
   
    private static final int[] INTENT_BASE_DAMAGE = {4, 2, 6, 11} ;
    private static final int[] ASC4_INTENT_BASE_DAMAGE = {4, 3, 7, 13};
    private static final int[] ASC19_INTENT_BASE_DAMAGE = {4, 3, 8, 15};
    
    public static class Attacks {
		public static final byte ATTACK_DEF = 0;
		public static final byte MULTIHIT = 1;
		public static final byte ATTACK = 2;
		public static final byte NUKE = 3;
    }
    
    public static class Pattern {
		public static final byte NUKE = 0;
		public static final byte LOAD = 1;
		public static final byte ATTACK = 2;
		public static final byte MULTIHIT = 3;
		public static final byte ATTACK_DEF = 4;
		public static final byte DEBUFF = 5;
		public static final byte BUFF = 6;
		public static final byte BLOCK = 7;
		public static final byte CHARGE = 8;
    }
    
    private static final float HB_X = 0.0F;
    private static final float HB_Y = 0.0F;
    private static final float HB_W = 330.0F;
    private static final float HB_H = 330.0F;
    
    public static final float OFFSET_X = -17.0F;
    public static final float OFFSET_Y = 0.0F;
    
    private static final float DIALOG_X = (-40.0F * Settings.scale);
    private static final float DIALOG_Y = (120.0F * Settings.scale);
    
    private static final int BLOCK = 20; 
    private static final int ARTIFACT = 1;
    private static final int ARTIFACT_BLOCK = 8;
    
	private static final int WEAK_AMOUNT = 2;
	private static final int BUFFER = 2; 
	
	private boolean startOfBattle = true;
	private boolean hasAttackedOnce = false;
	
	private int MULTI_ATTACKS_AMOUNT = 4;
	private int amountOfAttacks = 1;
	
	private int hpPrecharge = 0;
	private int hpPostcharge = 0;
	
	private int INTERRUPTION = 20;
	//private boolean ERROR_DETECTED = false;
	
    public Nu13(float offsetX, float offsetY) {
    	super(NAME, ID, MAX_HEALTH, HB_X, HB_Y, HB_W, HB_H, IMAGE, offsetX, offsetY);
    
    	this.type = EnemyType.BOSS;
        
        this.setAscHp();
        this.setAscDamage();
        
        this.dialogX = DIALOG_X;
        this.dialogY = DIALOG_Y;
        
    }
 

	private void setAscHp() {
		// TODO Auto-generated method stub
    	if (AbstractDungeon.ascensionLevel < 9) {
            this.setHp(MAX_HEALTH);
        }
        else {
            this.setHp(ASC9_MAX_HEALTH);
        }
	}
    
    private void setAscDamage() {
		// TODO Auto-generated method stub
    	if (AbstractDungeon.ascensionLevel < 4) {
    		setBaseDamage(INTENT_BASE_DAMAGE);
    	}
    	else if (AbstractDungeon.ascensionLevel < 19) {
    		setBaseDamage(ASC4_INTENT_BASE_DAMAGE);
    	}
    	else {
    		setBaseDamage(ASC19_INTENT_BASE_DAMAGE);
    	}
	}

	private void setBaseDamage(int[] intentBaseDamage) {
		// TODO Auto-generated method stub
		for (int i=0;i<intentBaseDamage.length;i++) {
			this.damage.add(new DamageInfo(this, intentBaseDamage[i]));
		}
	}

	@Override
	protected void getMove(int arg0) {
		// TODO Auto-generated method stub
		int choice = this.currentHealth % 3;
		
		boolean lastMoveWasNormalAttack = (this.lastMove(Pattern.ATTACK) || this.lastMove(Pattern.ATTACK_DEF) || this.lastMove(Pattern.MULTIHIT)); 
		boolean lastMoveWasNormalStatus = (this.lastMove(Pattern.BUFF) || this.lastMove(Pattern.DEBUFF) || this.lastMove(Pattern.BLOCK) );
				
		boolean COND_FIRST_ATTACK = this.lastMove(Pattern.LOAD);
		boolean COND_SECOND_ATTACK = lastMoveWasNormalStatus && this.hasAttackedOnce;
		
		boolean COND_ATTACK = COND_FIRST_ATTACK || COND_SECOND_ATTACK;
		boolean COND_STATUS = lastMoveWasNormalAttack;
		boolean COND_CHARGE = lastMoveWasNormalStatus && !this.hasAttackedOnce;
		
		getAmountOfAttacks();
		
		boolean isMultiDamage = (this.amountOfAttacks > 1) ? true : false;
		if (this.startOfBattle || this.lastMove(Pattern.CHARGE)) {
			setMove(MOVES[0], Pattern.NUKE, Intent.ATTACK_BUFF,(this.damage.get(Attacks.NUKE)).base,amountOfAttacks, isMultiDamage);
			this.startOfBattle = false;
		}
		else if (this.lastMove(Pattern.NUKE)) {
			setMove(MOVES[1], Pattern.LOAD, Intent.DEFEND_BUFF);
		}
		else if (COND_ATTACK) {
			this.hasAttackedOnce = !this.hasAttackedOnce;
			
			switch(choice) {
				case 0:
					setMove(MOVES[2], Pattern.ATTACK, Intent.ATTACK,(this.damage.get(Attacks.ATTACK)).base,amountOfAttacks, isMultiDamage);
					break;
				case 1:
					setMove(MOVES[3], Pattern.MULTIHIT, Intent.ATTACK,(this.damage.get(Attacks.MULTIHIT)).base,amountOfAttacks+MULTI_ATTACKS_AMOUNT, true);
					break;
				case 2:
					setMove(MOVES[4], Pattern.ATTACK_DEF, Intent.ATTACK_DEFEND,(this.damage.get(Attacks.ATTACK_DEF)).base,amountOfAttacks, isMultiDamage);
					break;
			}
		}
		else if (COND_STATUS) {
			
			switch(choice) {
				case 0:
					setMove(MOVES[5], Pattern.BLOCK, Intent.DEFEND);
					break;
				case 1:
					setMove(MOVES[6], Pattern.BUFF, Intent.BUFF);
					break;
				case 2:
					setMove(MOVES[7], Pattern.DEBUFF, Intent.DEBUFF);
					break;
			}
			
		}
		
		else if (COND_CHARGE) {
				this.hpPrecharge = this.currentHealth;
				setMove(Pattern.CHARGE, Intent.UNKNOWN);
		}
	}

	@Override
	public void takeTurn() {
		// TODO Auto-generated method stub
		getAmountOfAttacks();
		
		switch(this.nextMove) {
			case Pattern.NUKE:
				AbstractDungeon.actionManager.addToBottom(new TalkAction(this, DIALOG[0], 1.0F, 2.0F));		
				
			 	for (int i=0;i<amountOfAttacks;i++) {
					AbstractDungeon.actionManager.addToBottom(
				 			new DamageAction(
				 					AbstractDungeon.player, 
				 					damage.get(Attacks.NUKE), 
				 					AttackEffect.SLASH_HEAVY
				 	));
				}
			 	
			 	AbstractDungeon.actionManager.addToTop(
						new ApplyPowerAction(
								this,
								this,
								new Haku_UnlimitedDriveBossPower(
										this,1),1));
			 	
				break;
			case Pattern.LOAD:
				AbstractDungeon.actionManager.addToTop(
						new ApplyPowerAction(
								this,
								this,
								new ArtifactPower(
										this,1),1));
				AbstractDungeon.actionManager.addToBottom(
						new GainBlockAction(
								this,
								this, 
								ARTIFACT_BLOCK));	
				
				break;
			case Pattern.ATTACK:
				for (int i=0;i<amountOfAttacks;i++) {
					AbstractDungeon.actionManager.addToBottom(
				 			new DamageAction(
				 					AbstractDungeon.player, 
				 					damage.get(Attacks.ATTACK), 
				 					AttackEffect.SLASH_DIAGONAL
				 			));
				}	
				break;	
			case Pattern.MULTIHIT:
				for (int i=0;i<amountOfAttacks+MULTI_ATTACKS_AMOUNT;i++) {
					AbstractDungeon.actionManager.addToBottom(
				 			new DamageAction(
				 					AbstractDungeon.player, 
				 					damage.get(Attacks.MULTIHIT), 
				 					AttackEffect.BLUNT_LIGHT
				 			));
				}	
				break;	
			case Pattern.ATTACK_DEF:
				for (int i=0;i<amountOfAttacks;i++) {
					AbstractDungeon.actionManager.addToBottom(
				 			new DamageAction(
				 					AbstractDungeon.player, 
				 					damage.get(Attacks.ATTACK_DEF), 
				 					AttackEffect.SLASH_HEAVY
				 			));
					AbstractDungeon.actionManager.addToBottom(
							new GainBlockAction(
									this,
									this, 
									damage.get(Attacks.ATTACK_DEF).base));	
				}
				
				break;
			case Pattern.DEBUFF:
				AbstractDungeon.actionManager.addToBottom(
						new ApplyPowerAction(
								AbstractDungeon.player, this, 
								new WeakPower(
										AbstractDungeon.player, 
										WEAK_AMOUNT, true), 
								WEAK_AMOUNT)
						);
				break;
			case Pattern.BUFF:
				AbstractDungeon.actionManager.addToBottom(
						new ApplyPowerAction(
								this, 
								this,
								new Haku_DefensePower(this, BUFFER, true),
								BUFFER
								)
						);
				break;
			case Pattern.BLOCK:
				AbstractDungeon.actionManager.addToBottom(
						new GainBlockAction(
								this,
								this, 
								BLOCK));
				break;
			case Pattern.CHARGE:
				this.hpPostcharge = this.currentHealth;
				
				if (this.hpPrecharge - this.hpPostcharge > INTERRUPTION) {
					AbstractDungeon.actionManager.addToBottom(
							new TalkAction(this, DIALOG[1], 1.0F, 2.0F));	
					AbstractDungeon.actionManager.addToBottom(
						new ApplyPowerAction(
								this, this, 
								new WeakPower(
										this, 
										WEAK_AMOUNT, true), 
								WEAK_AMOUNT)
						);
				}
				else {
					AbstractDungeon.actionManager.addToBottom(
							new TalkAction(this, DIALOG[2], 1.0F, 2.0F));
				}
				AbstractDungeon.actionManager.addToBottom(new WaitAction(0.5F));
				
				
				break;
				
			default:
				break;
		}
		
		AbstractDungeon.actionManager.addToBottom(new RollMoveAction(this));
	}
	
	private void getAmountOfAttacks() {
		// TODO Auto-generated method stub
		this.amountOfAttacks = 1;
		if (this.hasPower(Haku_UnlimitedDriveBossPower.POWER_ID)) 
		{
			this.amountOfAttacks = 1 + this.getPower(Haku_UnlimitedDriveBossPower.POWER_ID).amount;
		}
	}


	@Override
    public void usePreBattleAction() {
        CardCrawlGame.music.unsilenceBGM();
        AbstractDungeon.scene.fadeOutAmbiance();
        AbstractDungeon.getCurrRoom().playBgmInstantly("BOSS_BOTTOM");
        
        AbstractDungeon.actionManager.addToBottom(
    			new ApplyPowerAction(this, this, new ArtifactPower(this, ARTIFACT), ARTIFACT));
	}
	
    public void die() {
        this.useFastShakeAnimation(5.0F);
        CardCrawlGame.screenShake.rumble(4.0F);
        super.die();
        AbstractDungeon.scene.fadeInAmbiance();
        this.onBossVictoryLogic();

    }

}
