package Hakumod.relics;

import basemod.abstracts.CustomRelic;
import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.helpers.ImageMaster;
import com.megacrit.cardcrawl.powers.*;
import com.megacrit.cardcrawl.relics.AbstractRelic;

public class Haku_Roundstart extends CustomRelic{
    public static final String RELIC_ID = "Haku_Roundstart";
    private static final String IMG = "Hakumod/img/relics/Roundstart.png";
    private static final String IMG_OTL = "Hakumod/img/relics/outline/Roundstart_outline.png";

    private int amount = 1;
	public Haku_Roundstart() {
		super(RELIC_ID, ImageMaster.loadImage(IMG), ImageMaster.loadImage(IMG_OTL), RelicTier.COMMON, LandingSound.MAGICAL);
		// TODO Auto-generated constructor stub
	}
	
    public String getUpdatedDescription() {
        return DESCRIPTIONS[0];
    }
    
    public AbstractRelic makeCopy() {
        return new Haku_Roundstart();
    }
    
	public void atBattleStart() {
		int buff = (int) Math.floor(Math.random()*5);
		
		switch (buff) {
			case 0:
				AbstractDungeon.actionManager.addToTop(new ApplyPowerAction(AbstractDungeon.player, AbstractDungeon.player, 
				new StrengthPower(AbstractDungeon.player, amount), amount));
				break;
			case 1:
				AbstractDungeon.actionManager.addToTop(new ApplyPowerAction(AbstractDungeon.player, AbstractDungeon.player, 
				new DexterityPower(AbstractDungeon.player, amount), amount));
				break;
			case 2:
				AbstractDungeon.actionManager.addToTop(new ApplyPowerAction(AbstractDungeon.player, AbstractDungeon.player, 
				new PlatedArmorPower(AbstractDungeon.player, amount+3), amount));
				break;
			case 3:
				AbstractDungeon.actionManager.addToTop(new ApplyPowerAction(AbstractDungeon.player, AbstractDungeon.player, 
				new ThornsPower(AbstractDungeon.player, amount+2), amount));
				break;
			case 4:
				AbstractDungeon.actionManager.addToTop(new ApplyPowerAction(AbstractDungeon.player, AbstractDungeon.player, 
				new IntangiblePlayerPower(AbstractDungeon.player, amount)));
				break;
		}
	}
}
