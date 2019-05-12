package Hakumod.potions;

import Hakumod.potions.abstracts.AbstractMagatamaPotion;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.localization.PotionStrings;
import com.megacrit.cardcrawl.potions.AbstractPotion;

public class HalfPowerPotion extends AbstractMagatamaPotion {
	
	public static final String POTION_ID = makePotionID(HalfPowerPotion.class.getSimpleName());
	private static final PotionStrings POTION_STRINGS = CardCrawlGame.languagePack.getPotionString(POTION_ID);
	public static final String NAME = POTION_STRINGS.NAME;
	private static final int MAGATAMA = 4;
	
	public HalfPowerPotion() {
		super(NAME, POTION_ID, PotionRarity.UNCOMMON, PotionSize.BOTTLE, PotionColor.WHITE, MAGATAMA);
	}
	
	@Override
	public AbstractPotion makeCopy() {
		return new HalfPowerPotion();
	}
}
