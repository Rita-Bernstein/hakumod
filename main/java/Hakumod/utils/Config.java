package Hakumod.utils;

import Hakumod.cards.Hakumen.ender.ClashAssault;
import Hakumod.cards.Hakumen.ender.Sweep;
import Hakumod.cards.Hakumen.fuumajin.CrystalWall;
import Hakumod.cards.Hakumen.fuumajin.Void;
import Hakumod.cards.Hakumen.fuumajin.Yomotsuhirasaka;
import Hakumod.cards.Hakumen.gatling.BlitzAttack;
import Hakumod.cards.Hakumen.gatling.Divide;
import Hakumod.cards.Hakumen.gatling.SmartSteer;
import Hakumod.cards.Hakumen.normal.ActiveFlow;
import Hakumod.cards.Hakumen.normal.ClawGrip;
import Hakumod.cards.Hakumen.normal.CommandOrder;
import Hakumod.cards.Hakumen.normal.FatalCounter;
import Hakumod.cards.Hakumen.parry.AlphaCounter;
import Hakumod.cards.Hakumen.parry.RejectGuard;
import Hakumod.cards.Hakumen.special.*;
import Hakumod.cards.Hakumen.starter.ExceedAccel;
import com.megacrit.cardcrawl.cards.AbstractCard;

import static com.megacrit.cardcrawl.core.CardCrawlGame.languagePack;

public class Config
{
	public final static String STARTING_CARDS = "starting-cards";
	public final static String USE_BOSSES = "use-boss";
	
    public final static String[][] LIST_STARTING_CARDS = 
    	{
    			{Guren.ID, Sweep.ID},
    			{BlitzAttack.ID, ClashAssault.ID},
    			{SmartSteer.ID, Renka.ID},
    			{Divide.ID, Zantetsu.ID},
    			{Kishuu.ID, Enma.ID},
    			{AlphaCounter.ID, RejectGuard.ID},
    			{ClawGrip.ID, ClawGrip.ID},
    			{ExceedAccel.ID, ActiveFlow.ID},
    			{CommandOrder.ID, FatalCounter.ID},
    			{Void.ID, CrystalWall.ID}
    	};
    
    public final static AbstractCard[] CARDS_COPY = 
    	{
    			new Sweep().makeCopy(),
    			new ClashAssault().makeCopy(),
    			new Renka().makeCopy(),
    			new Zantetsu().makeCopy(),
    			new Enma().makeCopy(),
    			new RejectGuard().makeCopy(),
    			new ClawGrip().makeCopy(),
    			new ExceedAccel().makeCopy(),
    			new CommandOrder().makeCopy(),
    			new Yomotsuhirasaka().makeCopy()
    	};
   
    public final static String[] CARDS_DESC = languagePack.getUIString(AssetsPath.makeID("AvailableCards")).TEXT;
    
    public final static float LABEL_START_X = 350.0f;
    public final static float LABEL_START_Y = 750.0f;

    public final static float CARDS_TOGGLE_START_X = 400.0f;
    public final static float CARDS_TOGGLE_START_Y = 700.0f;
    public final static float CARDS_SPACE_STEP = -40.0f;

    public final static float BOSSES_TOGGLE_START_X = 350.0f;
    public final static float BOSSES_TOGGLE_START_Y = 700.0F + (LIST_STARTING_CARDS.length+1)*CARDS_SPACE_STEP;
    public final static String TOGGLE_BOSSES_LABEL = languagePack.getUIString(AssetsPath.makeID("EnableBosses")).TEXT[0];

    
}