package Hakumod.potions;

import Hakumod.powers.Haku_MagatamaPower;
import basemod.abstracts.CustomPotion;
import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.helpers.PowerTip;
import com.megacrit.cardcrawl.localization.PotionStrings;
import com.megacrit.cardcrawl.potions.AbstractPotion;

public class Haku_AbstractMagatamaPotion extends CustomPotion {
	public static final String POTION_ID = "Haku_AbstractPowerPotion";
	private static final PotionStrings potionStrings = CardCrawlGame.languagePack.getPotionString(POTION_ID);
	public static final String NAME = potionStrings.NAME;
	public static final String[] DESCRIPTIONS = potionStrings.DESCRIPTIONS; 
	
	private int magatama = 0;
	
	
	public Haku_AbstractMagatamaPotion(String name, String id, PotionRarity rarity, PotionSize size,
			PotionColor color, int magatama) {
		super(name, id, rarity, size, color);
	    this.potency = getPotency();
	    this.isThrown = false;
		this.magatama = magatama; 
		
		this.description = DESCRIPTIONS[0] + this.magatama + DESCRIPTIONS[1];
		this.tips.add(new PowerTip(this.name, this.description));
	}

	@Override
	public int getPotency(int arg0) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public AbstractPotion makeCopy() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void use(AbstractCreature c) {
		// TODO Auto-generated method stub
		AbstractDungeon.actionManager.addToTop(
				new ApplyPowerAction(AbstractDungeon.player,AbstractDungeon.player,new Haku_MagatamaPower(AbstractDungeon.player,this.magatama),this.magatama));
	}


}
