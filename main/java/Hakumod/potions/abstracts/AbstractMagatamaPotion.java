package Hakumod.potions.abstracts;

import Hakumod.powers.player.MagatamaPower;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.helpers.PowerTip;
import com.megacrit.cardcrawl.localization.PotionStrings;
import com.megacrit.cardcrawl.potions.AbstractPotion;

public class AbstractMagatamaPotion extends AbstractHakuPotion {
	public static final String POTION_ID = makePotionID(AbstractMagatamaPotion.class.getSimpleName());
	private static final PotionStrings POTION_STRINGS = CardCrawlGame.languagePack.getPotionString(POTION_ID);
	public static final String NAME = POTION_STRINGS.NAME;
	public static final String[] DESCRIPTIONS = POTION_STRINGS.DESCRIPTIONS;
	private int magatama;
	
	public AbstractMagatamaPotion(String name, String id, PotionRarity rarity, PotionSize size,
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
		return 0;
	}

	@Override
	public AbstractPotion makeCopy() {
		return null;
	}

	@Override
	public void use(AbstractCreature c) {
		power(AbstractDungeon.player,AbstractDungeon.player,new MagatamaPower(AbstractDungeon.player,this.magatama),this.magatama);
	}
}
