package Hakumod.relics;

import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.helpers.ImageMaster;
import com.megacrit.cardcrawl.powers.StrengthPower;
import com.megacrit.cardcrawl.relics.AbstractRelic;

import basemod.abstracts.CustomRelic;

public class Haku_ChronoPhantasma extends CustomRelic{
    public static final String RELIC_ID = "Haku_ChronoPhantasma";
    private static final String IMG = "Hakumod/img/relics/ChronoPhantasma.png";
    private static final String IMG_OTL = "Hakumod/img/relics/outline/ChronoPhantasma_outline.png";
	private int HP_STACK = 15;
    
    //private boolean boolIncreaseDamage = false;
    //private float damageIncrease = 1.50F;
    //private ArrayList<AbstractCard> cardChoice;
    
	public Haku_ChronoPhantasma() {
		super(RELIC_ID, ImageMaster.loadImage(IMG), ImageMaster.loadImage(IMG_OTL), RelicTier.COMMON, LandingSound.MAGICAL);
		
		/*this.cardChoice = new ArrayList<AbstractCard>();
		cardChoice.add( new Haku_Kishuu().makeCopy() );
		cardChoice.add( new Haku_Enma().makeCopy());
		cardChoice.add( new Haku_Guren().makeCopy());
		cardChoice.add( new Haku_Renka().makeCopy());
		cardChoice.add( new Haku_CT().makeCopy());
		cardChoice.add( new Haku_Zantetsu().makeCopy());
		cardChoice.add( new Haku_Shippu().makeCopy());
		cardChoice.add( new Haku_Yukikaze().makeCopy());
		cardChoice.add( new Haku_Mugen().makeCopy());
		cardChoice.add( new Haku_Akumetsu().makeCopy());
		cardChoice.add( new Haku_Agito().makeCopy());
		cardChoice.add( new Haku_Hotaru().makeCopy());
		cardChoice.add( new Haku_Tsubaki().makeCopy());
		cardChoice.add( new Haku_RC().makeCopy());
		cardChoice.add( new Haku_CA().makeCopy());*/
	}
	
    public String getUpdatedDescription() {
        return DESCRIPTIONS[0];
    }
    
    public AbstractRelic makeCopy() {
        return new Haku_ChronoPhantasma();
    }
    
	public void atBattleStart() {
		// TODO Auto-generated method stub
		int hpMissing = AbstractDungeon.player.maxHealth - AbstractDungeon.player.currentHealth;
		int StrengthBonus = (int) ( (float) hpMissing / (float) HP_STACK);
		
		if (StrengthBonus > 0) {
			AbstractDungeon.actionManager.addToBottom(new ApplyPowerAction(AbstractDungeon.player, AbstractDungeon.player, 
					new StrengthPower(AbstractDungeon.player, StrengthBonus), StrengthBonus));
		}
	}
    
    
    
    /*public void onUseCard(AbstractCard card, UseCardAction action) { 	
    	if ( (card.cardID == "Haku_Overdrive") || (card.cardID == "Haku_ODC") || (card.cardID == "Haku_GCOD")){
    		AbstractDungeon.actionManager.addToBottom(
    				new ChooseCardAction(cardChoice, false, true, false, 0, false));

    	}
    }*/
    
   
}
