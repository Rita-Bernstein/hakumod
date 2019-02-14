package Hakumod.cards.Hakumen;

import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.GainBlockAction;
//import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
//import com.megacrit.cardcrawl.actions.common.DamageAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.localization.CardStrings;
//import com.megacrit.cardcrawl.localization.CardStrings;
import com.megacrit.cardcrawl.monsters.AbstractMonster;

import Hakumod.action.UsingSpecialAction;
import Hakumod.patches.AbstractCardEnum;
import Hakumod.patches.CustomTags;
import basemod.abstracts.CustomCard;
//import basemod.helpers.BaseModTags;
//import basemod.helpers.CardTags;
import basemod.helpers.CardTags;

public class Haku_Enma extends Haku_Special{

	public static final String ID = "Haku_Enma";
	
	private static final CardStrings cardStrings = CardCrawlGame.languagePack.getCardStrings(ID);
	public static final String NAME = cardStrings.NAME;
	public static final String RAW_DESCRIPTION = cardStrings.DESCRIPTION;
	//public static final String UPG_DESCRIPTION = cardStrings.UPGRADE_DESCRIPTION;
	
	public static final String IMG_PATH = "Hakumod/img/cards/Haku_Enma.png";
	private static final int COST = 1;
	private static final int ATTACK_DMG = 7;
	private static final int UPGRADE_PLUS_DMG = 2;
	
	public static int MAGATAMA_COST = 1;
	public int UPG_MAGATAMA_COST = 0;
	private static final int BLOCK = 7;
	private static final int UPGRADE_PLUS_BLOCK = 2;
	
	
	public Haku_Enma() {
		super(ID, NAME, IMG_PATH, COST, RAW_DESCRIPTION, 
				AbstractCard.CardType.ATTACK,
				AbstractCardEnum.HAKUMEN_COLOR,
				AbstractCard.CardRarity.COMMON,
				AbstractCard.CardTarget.ENEMY,
				MAGATAMA_COST);
		// TODO Auto-generated constructor stub
		this.baseDamage = ATTACK_DMG;
		this.baseBlock = BLOCK;
		this.magicNumber = this.baseMagicNumber = MAGATAMA_COST;
		this.tags.add(CustomTags.SPECIAL);
	}
	

	@Override
	public void upgrade() {
		// TODO Auto-generated method stub
		if (!this.upgraded) {
			upgradeName();
			upgradeDamage(UPGRADE_PLUS_DMG);
			upgradeBlock(UPGRADE_PLUS_BLOCK);
			setMagatamaCost(0);
			
		}
	}
	
	public void setMagatamaCost(int newCost) {
		this.magicNumber = cost;
	}
	
    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {	    		
		AbstractDungeon.actionManager.addToBottom( new UsingSpecialAction(p, MAGATAMA_COST));	
    	AbstractDungeon.actionManager.addToBottom(new com.megacrit.cardcrawl.actions.common.DamageAction(m,
				new DamageInfo(p, this.damage, this.damageTypeForTurn),
				AbstractGameAction.AttackEffect.BLUNT_LIGHT));
    	
    	AbstractDungeon.actionManager.addToBottom(new GainBlockAction(p, p, this.block));
    }
	
	public AbstractCard makeCopy() {
		return new Haku_Enma();
	}
}