package Hakumod.cards.Hakumen;

import Hakumod.cards.Hakumen.Utils.Haku_CustomCard;
import Hakumod.patches.AbstractCardEnum;
import Hakumod.powers.Haku_MagatamaPower;
import basemod.abstracts.CustomCard;
import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.actions.unique.LoseEnergyAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.localization.CardStrings;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.powers.DexterityPower;
import com.megacrit.cardcrawl.relics.ChemicalX;

//import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
//import com.megacrit.cardcrawl.actions.common.DamageAction;
//import com.megacrit.cardcrawl.localization.CardStrings;
//import Hakumod.action.ComboAction;

public class Haku_Xmatic extends Haku_CustomCard {

    public static final String ID = "Haku_Xmatic";

    private static final CardStrings cardStrings = CardCrawlGame.languagePack.getCardStrings(ID);
    public static final String NAME = cardStrings.NAME;
    public static final String RAW_DESCRIPTION = cardStrings.DESCRIPTION;
    public static final String UPG_DESCRIPTION = cardStrings.UPGRADE_DESCRIPTION;

    public static final String IMG_PATH = "Hakumod/img/cards/Haku_Xmatic.png";
    private static final int COST = -1;
    public final static int BUFF = 1;

    public Haku_Xmatic() {
        super(ID, NAME, IMG_PATH, COST, RAW_DESCRIPTION,
                AbstractCard.CardType.POWER,
                AbstractCardEnum.HAKUMEN_COLOR,
                AbstractCard.CardRarity.RARE,
                AbstractCard.CardTarget.SELF);
        this.baseMagicNumber = this.magicNumber = BUFF;
    }

    @Override
    public void upgrade() {
        // TODO Auto-generated method stub
        if (!this.upgraded) {
            upgradeName();
            //upgradeMagicNumber(UPGRADE_PLUS_DMG);
            this.rawDescription = UPG_DESCRIPTION;
            //this.exhaust = false;
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

        AbstractDungeon.actionManager.addToBottom(new ApplyPowerAction(p, p,
                new DexterityPower(p, energyConsumed), energyConsumed));
        AbstractDungeon.actionManager.addToBottom(new ApplyPowerAction(p, p,
                new Haku_MagatamaPower(p, energyConsumed), energyConsumed));

        AbstractDungeon.actionManager.addToBottom(new LoseEnergyAction(this.energyOnUse));
    }

    public AbstractCard makeCopy() {
        return new Haku_Xmatic();
    }



}