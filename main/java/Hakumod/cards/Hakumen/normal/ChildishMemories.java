package Hakumod.cards.Hakumen.normal;

import Hakumod.cards.Hakumen.abstracts.HakuCustomCard;
import Hakumod.patches.AbstractCardEnum;
import Hakumod.powers.player.MagatamaPower;
import com.megacrit.cardcrawl.actions.common.HealAction;
import com.megacrit.cardcrawl.actions.common.RemoveSpecificPowerAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.localization.CardStrings;
import com.megacrit.cardcrawl.monsters.AbstractMonster;

public class ChildishMemories extends HakuCustomCard {

	public static final String ID = makeCardID(ChildishMemories.class.getSimpleName());
	private static final CardStrings cardStrings = CardCrawlGame.languagePack.getCardStrings(ID);
	public static final String NAME = cardStrings.NAME;
	public static final String RAW_DESCRIPTION = cardStrings.DESCRIPTION;
	public static final String UPG_DESCRIPTION = cardStrings.UPGRADE_DESCRIPTION;

	private static final int COST = 0;
	private static final int MAGATAMA_LOSS = 1;
	private static final int BUFF = 4;
	private static final int UPGRADE_BUFF = 2;

	public ChildishMemories() {
		super(ID, NAME, COST, RAW_DESCRIPTION,
				AbstractCard.CardType.SKILL,
				AbstractCardEnum.HAKUMEN_COLOR,
				AbstractCard.CardRarity.RARE, 
				AbstractCard.CardTarget.SELF);
		this.magicNumber = this.baseMagicNumber = BUFF;
		this.exhaust = true;
		this.tags.add(CardTags.HEALING);
	}

	@Override
	public void upgrade() {
		if (!this.upgraded) {
			upgradeName();
			upgradeMagicNumber(UPGRADE_BUFF);
			this.rawDescription = UPG_DESCRIPTION;
			initializeDescription();
		}
	}

    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {
    	act(new HealAction(AbstractDungeon.player, AbstractDungeon.player, this.magicNumber));
		
    	if (AbstractDungeon.player.hasPower(MagatamaPower.POWER_ID))
		{
			AbstractDungeon.player.getPower(MagatamaPower.POWER_ID).reducePower(MAGATAMA_LOSS);
			AbstractDungeon.player.getPower(MagatamaPower.POWER_ID).updateDescription();
			if (AbstractDungeon.player.getPower(MagatamaPower.POWER_ID).amount == 0)
			{
				act(new RemoveSpecificPowerAction(AbstractDungeon.player, AbstractDungeon.player, MagatamaPower.POWER_ID));
			}
		}
    	
    }
	
	public AbstractCard makeCopy() {
		return new ChildishMemories();
	}
	
	
}