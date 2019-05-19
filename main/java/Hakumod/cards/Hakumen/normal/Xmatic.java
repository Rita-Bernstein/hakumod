package Hakumod.cards.Hakumen.normal;

import Hakumod.cards.Hakumen.abstracts.HakuCustomCard;
import Hakumod.patches.AbstractCardEnum;
import Hakumod.powers.player.MagatamaPower;
import com.megacrit.cardcrawl.actions.unique.LoseEnergyAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.localization.CardStrings;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.powers.StrengthPower;
import com.megacrit.cardcrawl.relics.ChemicalX;

public class Xmatic extends HakuCustomCard {

    public static final String ID = makeCardID(Xmatic.class.getSimpleName());
    private static final CardStrings cardStrings = CardCrawlGame.languagePack.getCardStrings(ID);
    public static final String NAME = cardStrings.NAME;
    public static final String RAW_DESCRIPTION = cardStrings.DESCRIPTION;
    public static final String UPG_DESCRIPTION = cardStrings.UPGRADE_DESCRIPTION;

    private static final int COST = -1;
    public final static int BUFF = 1;

    public Xmatic() {
        super(ID, NAME, COST, RAW_DESCRIPTION,
                AbstractCard.CardType.POWER,
                AbstractCardEnum.HAKUMEN_COLOR,
                AbstractCard.CardRarity.RARE,
                AbstractCard.CardTarget.SELF);
        this.baseMagicNumber = this.magicNumber = BUFF;
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

        int energyConsumed = this.energyOnUse;
        if (p.hasRelic(ChemicalX.ID)) {
            p.getRelic(ChemicalX.ID).flash();
            energyConsumed += 2;
        }

        if (this.upgraded){
            energyConsumed += 1;
        }

        power(p, p,
                new StrengthPower(p, energyConsumed), energyConsumed);
        power(p, p,
                new MagatamaPower(p, energyConsumed), energyConsumed);

        act(new LoseEnergyAction(this.energyOnUse));
    }

    public AbstractCard makeCopy() {
        return new Xmatic();
    }



}