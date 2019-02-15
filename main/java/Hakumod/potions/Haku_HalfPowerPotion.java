package Hakumod.potions;

import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.localization.PotionStrings;
import com.megacrit.cardcrawl.potions.AbstractPotion;

public class Haku_HalfPowerPotion extends Haku_AbstractMagatamaPotion{
	
	public static final String POTION_ID = "Haku_HalfPowerPotion";
	private static final PotionStrings potionStrings = CardCrawlGame.languagePack.getPotionString(POTION_ID);
	public static final String NAME = potionStrings.NAME;
	//public static final String[] DESCRIPTIONS = potionStrings.DESCRIPTIONS; 
	  
	private static final int MAGATAMA = 4;
	
	public Haku_HalfPowerPotion() {
		super(NAME, POTION_ID, PotionRarity.UNCOMMON, PotionSize.BOTTLE, PotionColor.WHITE, MAGATAMA);
	}
	
	@Override
	public AbstractPotion makeCopy() {
		// TODO Auto-generated method stub
		return new Haku_HalfPowerPotion();
	}

}
