package Hakumod.cards.Hakumen;

import Hakumod.cards.Hakumen.Utils.Haku_CustomCard;
import Hakumod.patches.AbstractCardEnum;
import Hakumod.powers.Haku_MagatamaPower;
import basemod.abstracts.CustomCard;
import com.megacrit.cardcrawl.actions.common.HealAction;
import com.megacrit.cardcrawl.actions.common.RemoveSpecificPowerAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.localization.CardStrings;
import com.megacrit.cardcrawl.monsters.AbstractMonster;

//import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
//import com.megacrit.cardcrawl.actions.common.DamageAction;
//import com.megacrit.cardcrawl.localization.CardStrings;
//import Hakumod.powers.MagatamaPower;


public class Haku_ChildishMemories extends Haku_CustomCard {

	public static final String ID = "Haku_ChildishMemories";
	
	private static final CardStrings cardStrings = CardCrawlGame.languagePack.getCardStrings(ID);
	public static final String NAME = cardStrings.NAME;
	public static final String RAW_DESCRIPTION = cardStrings.DESCRIPTION;
	public static final String UPG_DESCRIPTION = cardStrings.UPGRADE_DESCRIPTION;
	
	public static final String IMG_PATH = "Hakumod/img/cards/Haku_ChildishMemories.png";
	private static final int COST = 0;

	private final int MAGATAMA_LOSS = 2;
	//private static final int ATTACK_DMG = 4;
	//private static final int UPGRADE_PLUS_DMG = 2;
	//private static int CARD_TO_DRAW = 3;
	private int BUFF = 4;
	private static int UPGRADE_BUFF = 2;
	//private int MAX_HEAL = 8;
	//private static int UPGRADE_MAX = 10;
	    
	public Haku_ChildishMemories() {
		super(ID, NAME, IMG_PATH, COST, RAW_DESCRIPTION, 
				AbstractCard.CardType.SKILL,
				AbstractCardEnum.HAKUMEN_COLOR,
				AbstractCard.CardRarity.RARE, 
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
    	/*int amountHealed = (this.baseMagicNumber > MAX_HEAL) ? MAX_HEAL : this.baseMagicNumber; 
    	p.heal(amountHealed);*/
    	
    	AbstractDungeon.actionManager.addToBottom(new HealAction(AbstractDungeon.player, AbstractDungeon.player, this.magicNumber));
		
    	if (AbstractDungeon.player.hasPower(Haku_MagatamaPower.POWER_ID)) 
		{
			AbstractDungeon.player.getPower(Haku_MagatamaPower.POWER_ID).reducePower(MAGATAMA_LOSS);
			AbstractDungeon.player.getPower(Haku_MagatamaPower.POWER_ID).updateDescription();
			if (AbstractDungeon.player.getPower(Haku_MagatamaPower.POWER_ID).amount == 0) 
			{
				AbstractDungeon.actionManager.addToBottom(new RemoveSpecificPowerAction(AbstractDungeon.player, AbstractDungeon.player, Haku_MagatamaPower.POWER_ID));
			}
		}
    	
    }
	
	public AbstractCard makeCopy() {
		return new Haku_ChildishMemories();
	}
	
	
}