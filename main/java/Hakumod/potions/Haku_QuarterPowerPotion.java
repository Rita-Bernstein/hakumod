package Hakumod.potions;

import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.localization.PotionStrings;
import com.megacrit.cardcrawl.potions.AbstractPotion;

public class Haku_QuarterPowerPotion extends Haku_AbstractMagatamaPotion{
	
	public static final String POTION_ID = "Haku_QuarterPowerPotion";
	private static final PotionStrings potionStrings = CardCrawlGame.languagePack.getPotionString(POTION_ID);
	public static final String NAME = potionStrings.NAME;
	//public static final String[] DESCRIPTIONS = potionStrings.DESCRIPTIONS; 
	  
	private static final int MAGATAMA = 2;
	
	public Haku_QuarterPowerPotion() {
		super(NAME, POTION_ID, PotionRarity.COMMON, PotionSize.BOTTLE, PotionColor.BLUE, MAGATAMA);
	}
	
	@Override
	public AbstractPotion makeCopy() {
		// TODO Auto-generated method stub
		return new Haku_QuarterPowerPotion();
	}

}
