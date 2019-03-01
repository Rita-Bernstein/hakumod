package Hakumod.cards.Hakumen;

import Hakumod.action.StarterAction;
import Hakumod.action.UtilsApplyEffect;
import Hakumod.patches.AbstractCardEnum;
import Hakumod.patches.CustomTags;
import basemod.abstracts.CustomCard;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.localization.CardStrings;
import com.megacrit.cardcrawl.monsters.AbstractMonster;

//import com.megacrit.cardcrawl.actions.common.DamageAction;
//import com.megacrit.cardcrawl.localization.CardStrings;
//import basemod.helpers.BaseModTags;
//import basemod.helpers.CardTags;

public class Haku_4C extends CustomCard{

	public static final String ID = "Haku_4C";
	
	private static final CardStrings cardStrings = CardCrawlGame.languagePack.getCardStrings(ID);
	public static final String NAME = cardStrings.NAME;
	public static final String RAW_DESCRIPTION = cardStrings.DESCRIPTION;
	public static final String UPG_DESCRIPTION = cardStrings.UPGRADE_DESCRIPTION;
	
	public static final String IMG_PATH = "Hakumod/img/cards/Haku_4C.png";
	private static final int COST = 1;
	private static final int ATTACK_DMG = 6;
	private static final int UPGRADE_PLUS_DMG = 2;
	private static int BUFF = 1;
	    
	public Haku_4C() {
		super(ID, NAME, IMG_PATH, COST, RAW_DESCRIPTION, 
				AbstractCard.CardType.ATTACK,
				AbstractCardEnum.HAKUMEN_COLOR,
				AbstractCard.CardRarity.BASIC,
				AbstractCard.CardTarget.ENEMY);
		// TODO Auto-generated constructor stub
		this.baseDamage = ATTACK_DMG;
		this.magicNumber = this.baseMagicNumber = BUFF;
	}

	@Override
	public void upgrade() {
		// TODO Auto-generated method stub
		if (!this.upgraded) {
			upgradeName();
			upgradeDamage(UPGRADE_PLUS_DMG);
			this.rawDescription = UPG_DESCRIPTION;
			this.tags.add(CustomTags.ENDER);
			initializeDescription();
		}
	}

    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {
    	AbstractDungeon.actionManager.addToBottom(new com.megacrit.cardcrawl.actions.common.DamageAction(m,
				new DamageInfo(p, this.damage, this.damageTypeForTurn),
				AbstractGameAction.AttackEffect.SLASH_HORIZONTAL));
    	
    	if (this.upgraded) {
    		//AbstractDungeon.actionManager.addToTop(
    		//		new ApplyPowerAction(AbstractDungeon.player,AbstractDungeon.player,new MagatamaPower(AbstractDungeon.player,1),1));
        	/*AbstractDungeon.actionManager.addToTop(
        			new StarterAction(p, this, m, "neutral", this.magicNumber));*/
    		//AbstractDungeon.actionManager.addToBottom(new ChannelAction(new VoidOrb()));
    		AbstractDungeon.actionManager.addToTop(
        			new StarterAction(p, this, m, UtilsApplyEffect.FUUMAJIN, this.magicNumber));
    	}
    }
	
	public AbstractCard makeCopy() {
		return new Haku_4C();
	}
	
	public boolean isStrike() {
		return true;
	}
	
}
