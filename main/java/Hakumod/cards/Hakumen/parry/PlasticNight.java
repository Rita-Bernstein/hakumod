package Hakumod.cards.Hakumen.parry;

import Hakumod.action.ParryAction;
import Hakumod.action.UtilsApplyEffect;
import Hakumod.cards.Hakumen.abstracts.ParryCard;
import Hakumod.patches.AbstractCardEnum;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.localization.CardStrings;
import com.megacrit.cardcrawl.monsters.AbstractMonster;

public class PlasticNight extends ParryCard {
    public static final String ID = makeCardID(PlasticNight.class.getSimpleName());
    private static final CardStrings cardStrings = CardCrawlGame.languagePack.getCardStrings(ID);
    public static final String NAME = cardStrings.NAME;
    public static final String RAW_DESCRIPTION = cardStrings.DESCRIPTION;

    private static final int COST = 1;
    private static final int BLOCK = 4;
    private static final int UPGRADE_PLUS_BLOCK = 2;
    private static final int DMG = 0;

    public PlasticNight() {
        super(ID, NAME, COST, RAW_DESCRIPTION, CardType.ATTACK,
                AbstractCardEnum.HAKUMEN_COLOR,
                AbstractCard.CardRarity.COMMON,
                AbstractCard.CardTarget.ENEMY);
        this.baseDamage = DMG;
        this.baseBlock = BLOCK;
    }

    public void use(AbstractPlayer p, AbstractMonster m) {
        act(new ParryAction(p, null, m, UtilsApplyEffect.BLOCK, this.block));
        act(new ParryAction(p, null, m, UtilsApplyEffect.ATTACK, this.damage));
    }

    public AbstractCard makeCopy() {
        return new PlasticNight();
    }

    public void upgrade() {
        if (!this.upgraded) {
            upgradeName();
            upgradeBlock(UPGRADE_PLUS_BLOCK);
            initializeDescription();
        }
    }

    public int getDamage() {
        return ( DMG + ( this.block + AbstractDungeon.player.currentBlock) / 2);
    }

    private void updateDamage() {
        this.baseDamage = getDamage();
    }

    @Override
    public void applyPowers() {
        updateDamage();
        super.applyPowers();
    }

    @Override
    public void calculateCardDamage(AbstractMonster mo) {
        updateDamage();
        super.calculateCardDamage(mo);
    }

    @Override
    public void calculateDamageDisplay(AbstractMonster mo) {
        calculateCardDamage(mo);
        super.calculateDamageDisplay(mo);
    }
}
