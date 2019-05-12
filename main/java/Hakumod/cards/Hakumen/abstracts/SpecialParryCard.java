package Hakumod.cards.Hakumen.abstracts;

import Hakumod.action.canUseParry;
import Hakumod.patches.CustomTags;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;

public abstract class SpecialParryCard extends SpecialCard {
    public SpecialParryCard(String id, String name, int cost, String rawDescription, CardType type,
                            CardColor color, CardRarity rarity, CardTarget target, AbstractCard cardToPreview, int magatamaCost) {
        super(id, name, cost, rawDescription, type, color, rarity, target, cardToPreview, magatamaCost);
        this.tags.add(CustomTags.PARRY);
        this.tags.add(CustomTags.SPECIAL);
    }


    public SpecialParryCard(String id, String name, int cost, String rawDescription, CardType type,
                            CardColor color, CardRarity rarity, CardTarget target, int magatamaCost) {
        super(id, name, cost, rawDescription, type, color, rarity, target, magatamaCost);
        this.tags.add(CustomTags.PARRY);
        this.tags.add(CustomTags.SPECIAL);
    }

    @Override
    public boolean canUse(AbstractPlayer p, AbstractMonster m) {
        boolean canUseSpecial = super.canUse(p, m);
        //Udate the message after calling super.
        this.cantUseMessage = "This enemy doesn't intent to #rAttack.";
        return canUseSpecial && canUseParry.canUse(m);
    }
}
