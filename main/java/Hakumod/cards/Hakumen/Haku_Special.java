package Hakumod.cards.Hakumen;

import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.monsters.AbstractMonster;

import Hakumod.action.UsingSpecialAction;
import Hakumod.characters.Hakumen;
import basemod.abstracts.CustomCard;

public class Haku_Special extends CustomCard {
	
	public int MAGATAMA_COST = 2;
	
	public Haku_Special(String id, String name, String img, int cost, String rawDescription, CardType type,
			CardColor color, CardRarity rarity, CardTarget target, int magatamaCost) {
		super(id, name, img, cost, rawDescription, type, color, rarity, target);
		// TODO Auto-generated constructor stub
		this.MAGATAMA_COST = magatamaCost;
	}

	@Override
	public void upgrade() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void use(AbstractPlayer arg0, AbstractMonster arg1) {
		// TODO Auto-generated method stub
		
	}

	/* (non-Javadoc)
	 * @see com.megacrit.cardcrawl.cards.AbstractCard#canPlay(com.megacrit.cardcrawl.cards.AbstractCard)
	 */


	/* (non-Javadoc)
	 * @see com.megacrit.cardcrawl.cards.AbstractCard#canUse(com.megacrit.cardcrawl.characters.AbstractPlayer, com.megacrit.cardcrawl.monsters.AbstractMonster)
	 */
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

	/* (non-Javadoc)
	 * @see com.megacrit.cardcrawl.cards.AbstractCard#cardPlayable(com.megacrit.cardcrawl.monsters.AbstractMonster)
	 */
	/*@Override
	public boolean cardPlayable(AbstractMonster m) {
		// TODO Auto-generated method stub
		//return super.cardPlayable(m);
		if (AbstractDungeon.player instanceof Hakumen) {
			return new UsingSpecialAction(AbstractDungeon.player, MAGATAMA_COST).canUseSpecialAction();
		}
		else {
			return true;
		}
	}*/
	

}
