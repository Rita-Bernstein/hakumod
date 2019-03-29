package Hakumod.cards.Hakumen;

import Hakumod.action.UsingSpecialAction;
import Hakumod.cards.Hakumen.Utils.Haku_Special;
import Hakumod.patches.AbstractCardEnum;
import Hakumod.patches.CustomTags;
import Hakumod.powers.Haku_MugenPower;
import basemod.BaseMod;
import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.actions.common.MakeTempCardInHandAction;
import com.megacrit.cardcrawl.actions.unique.LoseEnergyAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.localization.CardStrings;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.relics.ChemicalX;

import java.util.ArrayList;

//import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
//import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
//import com.megacrit.cardcrawl.actions.common.DamageAction;
//import com.megacrit.cardcrawl.localization.CardStrings;


public class Haku_Mugen extends Haku_Special {

	public static final String ID = "Haku_Mugen";
	
	private static final CardStrings cardStrings = CardCrawlGame.languagePack.getCardStrings(ID);
	public static final String NAME = cardStrings.NAME;
	public static final String RAW_DESCRIPTION = cardStrings.DESCRIPTION;
	public static final String UPG_DESCRIPTION = cardStrings.UPGRADE_DESCRIPTION;
	
	public static final String IMG_PATH = "Hakumod/img/cards/Haku_Mugen.png";
	private static final int COST = -1;

	private static int CARD_TO_DRAW = 1;
	private static int MAGATAMA_COST = 8;
	
	public Haku_Mugen() {
		super(ID, NAME, IMG_PATH, COST, RAW_DESCRIPTION, 
				AbstractCard.CardType.POWER, 
				AbstractCardEnum.HAKUMEN_COLOR, 
				AbstractCard.CardRarity.RARE, 
				AbstractCard.CardTarget.SELF,
				MAGATAMA_COST);
		// TODO Auto-generated constructor stub
		this.tags.add(CustomTags.SPECIAL);
		this.magicNumber = this.baseMagicNumber = CARD_TO_DRAW;
	}

	@Override
	public void upgrade() {
		// TODO Auto-generated method stub
		if (!this.upgraded) {
			upgradeName();
			this.rawDescription = UPG_DESCRIPTION;
			initializeDescription();
		}
	}

    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {
		
		AbstractDungeon.actionManager.addToBottom( new UsingSpecialAction(p, MAGATAMA_COST));
    	int count = this.energyOnUse;
    	int randomCardNum;
    	AbstractCard cardSpecial;
    	
    	if (this.upgraded) {count++;}

		if (p.hasRelic(ChemicalX.ID)) {
			p.getRelic(ChemicalX.ID).flash();
			count += 2;
		}

    	AbstractCard [] randomSpecialPool = {
    			//new Haku_Kishuu().makeCopy(),
    			new Haku_Enma().makeCopy(),
    			new Haku_Guren().makeCopy(),
    			new Haku_Renka().makeCopy(),
				new Haku_CT().makeCopy(),
    			new Haku_Zantetsu().makeCopy(),
    			new Haku_Yukikaze().makeCopy(),
    			new Haku_Agito().makeCopy(),
    			new Haku_Hotaru().makeCopy(),
				new Haku_Yanagi().makeCopy(),
    			new Haku_Tsubaki().makeCopy(),
				new Haku_Shippu().makeCopy()
		};
    	
		for (AbstractCard cardInHand : p.hand.group) {
    		if (cardInHand.hasTag(CustomTags.SPECIAL)) {
    			cardInHand.setCostForTurn(0);
    		}
    	}
    
    	for (int i=0; i<count; i++) {
    		if (p.hand.size() == BaseMod.MAX_HAND_SIZE) {break;}
    		randomCardNum = AbstractDungeon.miscRng.random(0, randomSpecialPool.length -1 );
			cardSpecial = randomSpecialPool[randomCardNum].makeCopy();
    		cardSpecial.setCostForTurn(0);
    		act(new MakeTempCardInHandAction(cardSpecial, 1));
    		cardSpecial.lighten(false);
    		p.hand.refreshHandLayout();
    	}
    	
    	
    	AbstractDungeon.actionManager.addToBottom(new ApplyPowerAction(p, p, new Haku_MugenPower(p, 1, false), 1));
		AbstractDungeon.actionManager.addToBottom(new LoseEnergyAction(this.energyOnUse));
    }
	
	public AbstractCard makeCopy() {
		return new Haku_Mugen();
	}
	
	
}