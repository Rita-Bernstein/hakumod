package Hakumod.cards.Hakumen.abstracts;

import Hakumod.action.UsingSpecialAction;
import Hakumod.characters.Hakumen;
import Hakumod.patches.CustomTags;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.monsters.AbstractMonster;

public abstract class SpecialCard extends HakuCustomCard {
	
	private int magatamaCost;

	public SpecialCard(String id, String name, int cost, String rawDescription, CardType type,
					   CardColor color, CardRarity rarity, CardTarget target, AbstractCard cardToPreview, int magatamaCost) {
		super(id, name, cost, rawDescription, type, color, rarity, target, cardToPreview);
		this.magatamaCost = magatamaCost;
		this.tags.add(CustomTags.SPECIAL);
	}


	public SpecialCard(String id, String name, int cost, String rawDescription, CardType type,
					   CardColor color, CardRarity rarity, CardTarget target, int magatamaCost) {
		super(id, name, cost, rawDescription, type, color, rarity, target);
		this.magatamaCost = magatamaCost;
		this.tags.add(CustomTags.SPECIAL);
	}

	@Override
	public boolean canUse(AbstractPlayer p, AbstractMonster m) {
		if (AbstractDungeon.player instanceof Hakumen) {
			this.cantUseMessage = "Not enough #rMagatama.";
			return new UsingSpecialAction(AbstractDungeon.player, magatamaCost).canUseSpecialAction();
		}
		else {
			return true;
		}
	}
}
