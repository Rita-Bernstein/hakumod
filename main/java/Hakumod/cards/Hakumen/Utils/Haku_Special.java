package Hakumod.cards.Hakumen.Utils;

import Hakumod.action.UsingSpecialAction;
import Hakumod.characters.Hakumen;
import basemod.abstracts.CustomCard;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.monsters.AbstractMonster;

public abstract class Haku_Special extends Haku_CustomCard {
	
	public int MAGATAMA_COST = 2;


	public Haku_Special(String id, String name, String img, int cost, String rawDescription, CardType type,
						CardColor color, CardRarity rarity, CardTarget target, AbstractCard cardToPreview, int magatamaCost) {
		super(id, name, img, cost, rawDescription, type, color, rarity, target, cardToPreview);
		// TODO Auto-generated constructor stub
		this.MAGATAMA_COST = magatamaCost;
	}


	public Haku_Special(String id, String name, String img, int cost, String rawDescription, CardType type,
			CardColor color, CardRarity rarity, CardTarget target, int magatamaCost) {
		super(id, name, img, cost, rawDescription, type, color, rarity, target);
		// TODO Auto-generated constructor stub
		this.MAGATAMA_COST = magatamaCost;
	}

	@Override
	public boolean canUse(AbstractPlayer p, AbstractMonster m) {
		// TODO Auto-generated method stub
		//return super.canUse(p, m);
		if (AbstractDungeon.player instanceof Hakumen) {
			this.cantUseMessage = "Not enough #rMagatama.";
			return new UsingSpecialAction(AbstractDungeon.player, MAGATAMA_COST).canUseSpecialAction();
		}
		else {
			return true;
		}
	}
}
