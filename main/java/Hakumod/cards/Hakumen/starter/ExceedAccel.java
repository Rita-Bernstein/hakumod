package Hakumod.cards.Hakumen.starter;

import Hakumod.action.OkizemeAction;
import Hakumod.action.StarterAction;
import Hakumod.action.UtilsApplyEffect;
import Hakumod.cards.Hakumen.abstracts.HakuCustomCard;
import Hakumod.patches.AbstractCardEnum;
import Hakumod.patches.CustomTags;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.localization.CardStrings;
import com.megacrit.cardcrawl.monsters.AbstractMonster;

public class ExceedAccel extends HakuCustomCard {

	public static final String ID = makeCardID(ExceedAccel.class.getSimpleName());
	private static final CardStrings cardStrings = CardCrawlGame.languagePack.getCardStrings(ID);
	public static final String NAME = cardStrings.NAME;
	public static final String RAW_DESCRIPTION = cardStrings.DESCRIPTION;
	public static final String UPG_DESCRIPTION = cardStrings.UPGRADE_DESCRIPTION;

	private static final int COST = 0;
	private static final int ATTACK_DMG = 0;
	private static final int UPGRADE_PLUS_DMG = 2;
	private static final int BLOCK = 0;
	private static final int UPGRADE_PLUS_BLOCK = 2;

	public ExceedAccel() {
		super(ID, NAME, COST, RAW_DESCRIPTION,
				AbstractCard.CardType.ATTACK,
				AbstractCardEnum.HAKUMEN_COLOR,
				AbstractCard.CardRarity.UNCOMMON,
				AbstractCard.CardTarget.ENEMY);
		this.baseDamage = ATTACK_DMG;
		this.baseBlock = BLOCK;
		this.tags.add(CustomTags.STARTER);
		this.tags.add(CustomTags.ENDER);
	}

	@Override
	public void upgrade() {
		if (!this.upgraded) {
			upgradeName();
			upgradeDamage(UPGRADE_PLUS_DMG);
			upgradeBlock(UPGRADE_PLUS_BLOCK);
			this.rawDescription = UPG_DESCRIPTION;
			initializeDescription();
		}
	}

	public int getBlock(){
		return AbstractDungeon.player.energy.energy + this.baseBlock;
	}

	public int getDamage() {
		return  AbstractDungeon.player.cardsPlayedThisTurn + this.baseDamage;
	}

	private void updateValues(){
		this.baseDamage = getDamage();
		this.baseBlock = getBlock();
	}

	@Override
	public void applyPowers() {
		updateValues();
		super.applyPowers();
	}

	@Override
	public void calculateDamageDisplay(AbstractMonster mo) {
		calculateCardDamage(mo);
		super.calculateDamageDisplay(mo);
	}

	@Override
	public void calculateCardDamage(AbstractMonster mo) {
		updateValues();
		super.calculateCardDamage(mo);
	}

    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {
		act(new OkizemeAction(p, this, m, UtilsApplyEffect.ATTACK, this.damage));
		act(new StarterAction(p, this, m, UtilsApplyEffect.BLOCK, this.block));
    }
	
	public AbstractCard makeCopy() {
		return new ExceedAccel();
	}
}