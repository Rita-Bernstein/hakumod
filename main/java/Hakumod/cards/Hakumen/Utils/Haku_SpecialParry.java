package Hakumod.cards.Hakumen.Utils;

import Hakumod.action.UsingSpecialAction;
import Hakumod.action.canUseParry;
import Hakumod.characters.Hakumen;
import basemod.abstracts.CustomCard;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.monsters.AbstractMonster;

public abstract class Haku_SpecialParry extends Haku_Special {
    public Haku_SpecialParry(String id, String name, String img, int cost, String rawDescription, CardType type,
                        CardColor color, CardRarity rarity, CardTarget target, AbstractCard cardToPreview, int magatamaCost) {
        super(id, name, img, cost, rawDescription, type, color, rarity, target, cardToPreview, magatamaCost);
    }


    public Haku_SpecialParry(String id, String name, String img, int cost, String rawDescription, CardType type,
                        CardColor color, CardRarity rarity, CardTarget target, int magatamaCost) {
        super(id, name, img, cost, rawDescription, type, color, rarity, target, magatamaCost);

    }

    @Override
    public boolean canUse(AbstractPlayer p, AbstractMonster m) {
        boolean canUseSpecial = super.canUse(p, m);
        //Udate the message after calling super.
        this.cantUseMessage = "This enemy doesn't intent to #rAttack.";
        return canUseSpecial && canUseParry.canUse(m);
    }
}
