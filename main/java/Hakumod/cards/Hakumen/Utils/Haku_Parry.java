package Hakumod.cards.Hakumen.Utils;

import Hakumod.action.UsingSpecialAction;
import Hakumod.action.canUseParry;
import Hakumod.characters.Hakumen;
import basemod.abstracts.CustomCard;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.monsters.AbstractMonster;

public abstract class Haku_Parry extends Haku_CustomCard {

    public Haku_Parry(String id, String name, String img, int cost, String rawDescription, CardType type,
                        CardColor color, CardRarity rarity, CardTarget target, AbstractCard cardToPreview) {
        super(id, name, img, cost, rawDescription, type, color, rarity, target, cardToPreview);
    }


    public Haku_Parry(String id, String name, String img, int cost, String rawDescription, CardType type,
                        CardColor color, CardRarity rarity, CardTarget target) {
        super(id, name, img, cost, rawDescription, type, color, rarity, target);
    }

    @Override
    public boolean canUse(AbstractPlayer p, AbstractMonster m) {
        this.cantUseMessage = "This enemy doesn't intent to #rAttack.";
        return super.canUse(p, m) && canUseParry.canUse(m);
    }
}
