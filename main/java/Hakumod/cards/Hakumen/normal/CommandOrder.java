package Hakumod.cards.Hakumen.normal;

import Hakumod.action.UpgradeHandAction;
import Hakumod.cards.Hakumen.abstracts.HakuCustomCard;
import Hakumod.patches.AbstractCardEnum;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.actions.common.DrawCardAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.localization.CardStrings;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.powers.StrengthPower;

public class CommandOrder extends HakuCustomCard {

	public static final String ID = makeCardID(CommandOrder.class.getSimpleName());
	private static final CardStrings cardStrings = CardCrawlGame.languagePack.getCardStrings(ID);
	public static final String NAME = cardStrings.NAME;
	public static final String RAW_DESCRIPTION = cardStrings.DESCRIPTION;

	private static final int COST = 2;
	private static final int ATTACK_DMG = 10;
	private static final int UPGRADE_PLUS_DMG = 3;
	private static final int BUFF = 1;

	public CommandOrder() {
		super(ID, NAME, COST, RAW_DESCRIPTION,
				AbstractCard.CardType.ATTACK,
				AbstractCardEnum.HAKUMEN_COLOR,
				AbstractCard.CardRarity.UNCOMMON,
				AbstractCard.CardTarget.ENEMY);
		this.baseDamage = ATTACK_DMG;
		this.magicNumber = this.baseMagicNumber = BUFF;
		this.timesUpgraded = 0;
	}

	@Override
	public boolean canUpgrade() {
		return true;
	}

	@Override
	public void upgrade() {
		this.timesUpgraded++;
		this.upgraded = true;
		this.name = NAME + "+" + this.timesUpgraded;
		upgradeDamage(UPGRADE_PLUS_DMG);
		upgradeBaseCost(1);
	}

    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {
		act(new com.megacrit.cardcrawl.actions.common.DamageAction(m,
				new DamageInfo(p, this.damage, this.damageTypeForTurn),
				AbstractGameAction.AttackEffect.SLASH_HEAVY));
    	
		if (this.timesUpgraded >= 2) {
			act(new DrawCardAction(AbstractDungeon.player, this.magicNumber));
		}
		if (this.timesUpgraded >= 3) {
			act(new ApplyPowerAction(p, p,
					new StrengthPower(p, this.magicNumber), this.magicNumber));
		}
		
		if (this.timesUpgraded >= 4) {
			act(new UpgradeHandAction(AbstractDungeon.player));
		}
    }
	
	public AbstractCard makeCopy() {
		return new CommandOrder();
	}
	
	
}