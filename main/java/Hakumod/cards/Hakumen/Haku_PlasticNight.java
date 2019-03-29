package Hakumod.cards.Hakumen;

import Hakumod.action.ParryAction;
import Hakumod.action.UtilsApplyEffect;
import Hakumod.action.canUseParry;
import Hakumod.cards.Hakumen.Utils.Haku_CustomCard;
import Hakumod.cards.Hakumen.Utils.Haku_Parry;
import Hakumod.patches.AbstractCardEnum;
import Hakumod.patches.CustomTags;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.localization.CardStrings;
import com.megacrit.cardcrawl.monsters.AbstractMonster;

public class Haku_PlasticNight extends Haku_Parry {
    public static final String ID = "Haku_PlasticNight";

    private static final CardStrings cardStrings = CardCrawlGame.languagePack.getCardStrings(ID);
    public static final String NAME = cardStrings.NAME;
    public static final String RAW_DESCRIPTION = cardStrings.DESCRIPTION;

    public static final String IMG_PATH = "Hakumod/img/cards/Haku_PlasticNight.png";

    private static final int COST = 1;
    private static final int BLOCK = 4;
    private static final int UPGRADE_PLUS_BLOCK = 2;

    private static final int DMG = 0;



    public Haku_PlasticNight () {
        super(ID, NAME, IMG_PATH, COST, RAW_DESCRIPTION, CardType.ATTACK,
                AbstractCardEnum.HAKUMEN_COLOR,
                AbstractCard.CardRarity.COMMON,
                AbstractCard.CardTarget.ENEMY);
        this.baseDamage = DMG;
        this.baseBlock = BLOCK;
        this.tags.add(CustomTags.PARRY);
    }

    public void use(AbstractPlayer p, AbstractMonster m) {
        AbstractDungeon.actionManager.addToBottom(new ParryAction(p, null, m, UtilsApplyEffect.BLOCK, this.block));

        AbstractDungeon.actionManager.addToBottom(new ParryAction(p, null, m, UtilsApplyEffect.ATTACK, this.damage));
    }

    public AbstractCard makeCopy() {
        return new Haku_PlasticNight();
    }

    public void upgrade() {
        if (!this.upgraded) {
            upgradeName();
            upgradeBlock(UPGRADE_PLUS_BLOCK);
            initializeDescription();
        }
    }

    public int getDamage() {
        return ( this.baseDamage + ( this.block + AbstractDungeon.player.currentBlock) / 2);
    }

    public void updateDamage() {
        this.damage = getDamage();
        if (this.damage > 0) {this.isDamageModified = true;}
    }

    //Display damage when the card is in the hand.
    @Override
    public void applyPowers() {
        super.applyPowers();
        updateDamage();
    }

    @Override
    public void calculateCardDamage(AbstractMonster mo) {
        super.calculateCardDamage(mo);
        updateDamage();
    }

    @Override
    public void calculateDamageDisplay(AbstractMonster mo) {
        // TODO Auto-generated method stub
        super.calculateDamageDisplay(mo);
        calculateCardDamage(mo);
    }
}
