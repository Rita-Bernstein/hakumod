package Hakumod.cards.Hakumen.normal;

import Hakumod.cards.Hakumen.abstracts.HakuCustomCard;
import Hakumod.patches.AbstractCardEnum;
import Hakumod.powers.player.MagatamaPower;
import com.megacrit.cardcrawl.actions.common.GainBlockAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.localization.CardStrings;
import com.megacrit.cardcrawl.monsters.AbstractMonster;

public class InstantBlock extends HakuCustomCard {
	public static final String ID = makeCardID(InstantBlock.class.getSimpleName());
	private static final CardStrings cardStrings = CardCrawlGame.languagePack.getCardStrings(ID);
	public static final String NAME = cardStrings.NAME;
	public static final String RAW_DESCRIPTION = cardStrings.DESCRIPTION;
	
	private static final int COST = 1;
	private static final int BLOCK = 5;
	private static final int UPGRADE_PLUS_BLOCK = 3;
	private static final int BUFF = 1;
	private static final int UPGRADE_BUFF = 1;
	
	public InstantBlock() {
		super(ID, NAME, COST, RAW_DESCRIPTION,
				AbstractCard.CardType.SKILL,
				AbstractCardEnum.HAKUMEN_COLOR,
				AbstractCard.CardRarity.UNCOMMON,
				AbstractCard.CardTarget.SELF);
		this.baseBlock = BLOCK;
		this.magicNumber = this.baseMagicNumber = BUFF;
	}
	
	public void use(AbstractPlayer p, AbstractMonster m) {
		act(new GainBlockAction(p, p, this.block));
		power(p, p, new MagatamaPower(AbstractDungeon.player,1),1);
	}
	
	public AbstractCard makeCopy() {
		return new InstantBlock();
	}
	
	public void upgrade() {
		if (!this.upgraded) {
			upgradeName();
			upgradeBlock(UPGRADE_PLUS_BLOCK);
			upgradeMagicNumber(UPGRADE_BUFF);
			initializeDescription();
		}
	}
}
