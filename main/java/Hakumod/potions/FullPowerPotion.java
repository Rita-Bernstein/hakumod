package Hakumod.potions;

import Hakumod.potions.abstracts.AbstractMagatamaPotion;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.localization.PotionStrings;
import com.megacrit.cardcrawl.potions.AbstractPotion;

public class FullPowerPotion extends AbstractMagatamaPotion {
	
	public static final String POTION_ID = makePotionID(FullPowerPotion.class.getSimpleName());
	private static final PotionStrings potionStrings = CardCrawlGame.languagePack.getPotionString(POTION_ID);
	public static final String NAME = potionStrings.NAME;

	private static final int MAGATAMA = 8;
	
	public FullPowerPotion() {
		super(NAME, POTION_ID, PotionRarity.RARE, PotionSize.BOTTLE, PotionColor.FEAR, MAGATAMA);
	}
	
	@Override
	public AbstractPotion makeCopy() {
		return new FullPowerPotion();
	}

}
