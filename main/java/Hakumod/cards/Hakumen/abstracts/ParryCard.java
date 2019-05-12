package Hakumod.cards.Hakumen.abstracts;

import Hakumod.action.canUseParry;
import Hakumod.patches.CustomTags;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;

public abstract class ParryCard extends HakuCustomCard {

    public ParryCard(String id, String name, int cost, String rawDescription, CardType type,
                     CardColor color, CardRarity rarity, CardTarget target, AbstractCard cardToPreview) {
        super(id, name, cost, rawDescription, type, color, rarity, target, cardToPreview);
        this.tags.add(CustomTags.PARRY);
    }


    public ParryCard(String id, String name, int cost, String rawDescription, CardType type,
                     CardColor color, CardRarity rarity, CardTarget target) {
        super(id, name, cost, rawDescription, type, color, rarity, target);
        this.tags.add(CustomTags.PARRY);
    }

    @Override
    public boolean canUse(AbstractPlayer p, AbstractMonster m) {
        this.cantUseMessage = "This enemy doesn't intent to #rAttack.";
        return super.canUse(p, m) && canUseParry.canUse(m);
    }
}
