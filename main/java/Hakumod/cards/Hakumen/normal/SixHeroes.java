package Hakumod.cards.Hakumen.normal;

import Hakumod.cards.Hakumen.abstracts.HakuCustomCard;
import Hakumod.patches.AbstractCardEnum;
import Hakumod.powers.player.MagatamaPower;
import com.megacrit.cardcrawl.actions.common.GainEnergyAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.localization.CardStrings;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.powers.*;

public class SixHeroes extends HakuCustomCard {

	public static final String ID = makeCardID(SixHeroes.class.getSimpleName());
	private static final CardStrings cardStrings = CardCrawlGame.languagePack.getCardStrings(ID);
	public static final String NAME = cardStrings.NAME;
	public static final String RAW_DESCRIPTION = cardStrings.DESCRIPTION;
	public static final String UPG_DESCRIPTION = cardStrings.UPGRADE_DESCRIPTION;

	private static final int COST = 3;
	private static final int DRAW = 1;
	private static final int ENERGY = 1;
	private static final int STRENGTH = 1;
	private static final int PLATED_ARMOR = 2;
	private static final int THORNS = 1;
	private static final int ARTIFACT = 1;
	private static final int BLOCK = 6;
	private static final int UPG_BLOCK = 3;
	private static final  int UPGRADE_BUFF = 1;
	    
	public SixHeroes() {
		super(ID, NAME, COST, RAW_DESCRIPTION,
				AbstractCard.CardType.POWER,
				AbstractCardEnum.HAKUMEN_COLOR,
				AbstractCard.CardRarity.RARE,
				AbstractCard.CardTarget.SELF);
		this.magicNumber = this.baseMagicNumber = DRAW;
		this.baseBlock = BLOCK;
	}

	@Override
	public void upgrade() {
		if (!this.upgraded) {
			upgradeName();
			upgradeMagicNumber(UPGRADE_BUFF);
			this.upgradeBlock(UPG_BLOCK);
			this.rawDescription = UPG_DESCRIPTION;
			initializeDescription();
		}
	}

    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {
		act(new GainEnergyAction(ENERGY));
    	power(p, p,
				new ArtifactPower(AbstractDungeon.player, ARTIFACT), ARTIFACT);
    	power(p, p,
				new StrengthPower(p, this.magicNumber), this.magicNumber);
    	power(p, p,
				new PlatedArmorPower(p, PLATED_ARMOR), PLATED_ARMOR);
    	power(p, p,
				new ThornsPower(AbstractDungeon.player, THORNS), THORNS);
		power(p, p,
				new MagatamaPower(p, this.magicNumber), this.magicNumber);
    }
	
	public AbstractCard makeCopy() {
		return new SixHeroes();
	}
}