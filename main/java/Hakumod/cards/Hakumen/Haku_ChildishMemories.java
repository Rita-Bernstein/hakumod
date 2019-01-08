package Hakumod.cards.Hakumen;

import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.actions.common.DrawCardAction;
import com.megacrit.cardcrawl.actions.common.GainBlockAction;
//import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
//import com.megacrit.cardcrawl.actions.common.DamageAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.cards.AbstractCard.CardTags;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.localization.CardStrings;
//import com.megacrit.cardcrawl.localization.CardStrings;
import com.megacrit.cardcrawl.monsters.AbstractMonster;

import Hakumod.action.ComboAction;
import Hakumod.patches.AbstractCardEnum;
import Hakumod.powers.Haku_MagatamaPower;
//import Hakumod.powers.MagatamaPower;
import basemod.abstracts.CustomCard;
import Hakumod.patches.CustomTags;
//import basemod.helpers.BaseModTags;
//import basemod.helpers.CardTags;


public class Haku_ChildishMemories extends CustomCard{

	public static final String ID = "Haku_ChildishMemories";
	
	private static final CardStrings cardStrings = CardCrawlGame.languagePack.getCardStrings(ID);
	public static final String NAME = cardStrings.NAME;
	public static final String RAW_DESCRIPTION = cardStrings.DESCRIPTION;
	public static final String UPG_DESCRIPTION = cardStrings.UPGRADE_DESCRIPTION;
	
	public static final String IMG_PATH = "Hakumod/img/cards/Haku_ChildishMemories.png";
	private static final int COST = 0;
	//private static final int ATTACK_DMG = 4;
	//private static final int UPGRADE_PLUS_DMG = 2;
	//private static int CARD_TO_DRAW = 3;
	private int BUFF = 0;
	private static int UPGRADE_BUFF = 2;
	private int MAX_HEAL = 8;
	//private static int UPGRADE_MAX = 10;
	    
	public Haku_ChildishMemories() {
		super(ID, NAME, IMG_PATH, COST, RAW_DESCRIPTION, 
				AbstractCard.CardType.SKILL,
				AbstractCardEnum.HAKUMEN_COLOR,
				AbstractCard.CardRarity.UNCOMMON, 
				AbstractCard.CardTarget.SELF);
		// TODO Auto-generated constructor stub
		//this.baseDamage = ATTACK_DMG;
		this.magicNumber = this.baseMagicNumber = BUFF;
		this.exhaust = true;
		this.tags.add(CardTags.HEALING);
	}

	@Override
	public void upgrade() {
		// TODO Auto-generated method stub
		if (!this.upgraded) {
			upgradeName();
			//upgradeDamage(UPGRADE_PLUS_DMG);
			upgradeMagicNumber(UPGRADE_BUFF);
			this.rawDescription = UPG_DESCRIPTION;
			//initializeDescription();
			//this.exhaust = false;
			initializeDescription();
		}
	}

    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {
    	//AbstractDungeon.actionManager.addToBottom(new DrawCardAction(AbstractDungeon.player, 3));
    	//p.draw(3);
    	/*int cardInHand = p.hand.size();
    	AbstractDungeon.actionManager.addToBottom(new GainBlockAction(p, p, cardInHand));
		*/
    	/*for (AbstractCard cardIsSpecial: p.hand.group) {
			if (cardIsSpecial.hasTag(CustomTags.SPECIAL)){
				AbstractDungeon.actionManager.addToBottom(new GainBlockAction(p, p, this.magicNumber));
			}
    	}*/
    	int amountHealed = (this.baseMagicNumber > MAX_HEAL) ? MAX_HEAL : this.baseMagicNumber; 
    	p.heal(amountHealed);
    	
    }
	
	public AbstractCard makeCopy() {
		return new Haku_ChildishMemories();
	}
	
	
}