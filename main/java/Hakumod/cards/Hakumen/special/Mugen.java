package Hakumod.cards.Hakumen.special;

import Hakumod.action.UsingSpecialAction;
import Hakumod.cards.Hakumen.abstracts.SpecialCard;
import Hakumod.cards.Hakumen.parry.Yanagi;
import Hakumod.patches.AbstractCardEnum;
import Hakumod.patches.CustomTags;
import Hakumod.powers.player.MugenPower;
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

public class Mugen extends SpecialCard {

	public static final String ID = makeCardID(Mugen.class.getSimpleName());
	private static final CardStrings cardStrings = CardCrawlGame.languagePack.getCardStrings(ID);
	public static final String NAME = cardStrings.NAME;
	public static final String RAW_DESCRIPTION = cardStrings.DESCRIPTION;
	public static final String UPG_DESCRIPTION = cardStrings.UPGRADE_DESCRIPTION;
	;
	private static final int COST = -1;
	private static final int CARD_TO_DRAW = 1;
	private static final int MAGATAMA_COST = 8;
	
	public Mugen() {
		super(ID, NAME, COST, RAW_DESCRIPTION,
				AbstractCard.CardType.POWER, 
				AbstractCardEnum.HAKUMEN_COLOR, 
				AbstractCard.CardRarity.RARE, 
				AbstractCard.CardTarget.SELF,
				MAGATAMA_COST);
		this.magicNumber = this.baseMagicNumber = CARD_TO_DRAW;
	}

	@Override
	public void upgrade() {
		if (!this.upgraded) {
			upgradeName();
			this.rawDescription = UPG_DESCRIPTION;
			initializeDescription();
		}
	}

    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {
		act(new UsingSpecialAction(p, MAGATAMA_COST));
    	int count = this.energyOnUse;
    	int randomCardNum;
    	AbstractCard cardSpecial;
    	
    	if (this.upgraded) {count++;}

		if (p.hasRelic(ChemicalX.ID)) {
			p.getRelic(ChemicalX.ID).flash();
			count += 2;
		}

    	AbstractCard [] randomSpecialPool = {
			new Enma().makeCopy(),
			new Guren().makeCopy(),
			new Renka().makeCopy(),
			new CrushTrigger().makeCopy(),
			new Zantetsu().makeCopy(),
			new Yukikaze().makeCopy(),
			new Agito().makeCopy(),
			new Hotaru().makeCopy(),
			new Yanagi().makeCopy(),
			new Tsubaki().makeCopy(),
			new Shippu().makeCopy()
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

    	act(new ApplyPowerAction(p, p, new MugenPower(p, 1, false), 1));
		act(new LoseEnergyAction(this.energyOnUse));
    }
	
	public AbstractCard makeCopy() {
		return new Mugen();
	}
}