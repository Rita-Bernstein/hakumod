package Hakumod.potions;

import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.localization.PotionStrings;
import com.megacrit.cardcrawl.potions.AbstractPotion;

public class Haku_FullPowerPotion extends Haku_AbstractMagatamaPotion{
	
	public static final String POTION_ID = "Haku_FullPowerPotion";
	private static final PotionStrings potionStrings = CardCrawlGame.languagePack.getPotionString(POTION_ID);
	public static final String NAME = potionStrings.NAME;
	//public static final String[] DESCRIPTIONS = potionStrings.DESCRIPTIONS; 
	  
	private static final int MAGATAMA = 8;
	
	public Haku_FullPowerPotion() {
		super(NAME, POTION_ID, PotionRarity.RARE, PotionSize.BOTTLE, PotionColor.FEAR, MAGATAMA);
	}
	
	@Override
	public AbstractPotion makeCopy() {
		// TODO Auto-generated method stub
		return new Haku_FullPowerPotion();
	}

}
