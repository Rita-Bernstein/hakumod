package Hakumod;

import com.megacrit.cardcrawl.cards.AbstractCard;

import Hakumod.cards.Hakumen.Haku_2A;
import Hakumod.cards.Hakumen.Haku_2B;
import Hakumod.cards.Hakumen.Haku_2D;
import Hakumod.cards.Hakumen.Haku_3C;
import Hakumod.cards.Hakumen.Haku_5C;
import Hakumod.cards.Hakumen.Haku_6A;
import Hakumod.cards.Hakumen.Haku_6B;
import Hakumod.cards.Hakumen.Haku_6C;
import Hakumod.cards.Hakumen.Haku_ActiveFlow;
import Hakumod.cards.Hakumen.Haku_EA;
import Hakumod.cards.Hakumen.Haku_Enma;
import Hakumod.cards.Hakumen.Haku_FC;
import Hakumod.cards.Hakumen.Haku_Guren;
import Hakumod.cards.Hakumen.Haku_J2A;
import Hakumod.cards.Hakumen.Haku_JD;
import Hakumod.cards.Hakumen.Haku_Kishuu;
import Hakumod.cards.Hakumen.Haku_Renka;
import Hakumod.cards.Hakumen.Haku_Void;
import Hakumod.cards.Hakumen.Haku_Yomotsuhirasaka;
import Hakumod.cards.Hakumen.Haku_Zantetsu;

public class Config
{
	public final static String STARTING_CARDS = "starting-cards";
	public final static String USE_BOSSES = "use-boss";
	
    public final static String[][] LIST_STARTING_CARDS = 
    	{
    			{Haku_Guren.ID, Haku_3C.ID},
    			{Haku_6A.ID, Haku_6B.ID},
    			{Haku_2B.ID, Haku_Renka.ID},
    			{Haku_5C.ID, Haku_Zantetsu.ID},
    			{Haku_Kishuu.ID, Haku_Enma.ID},
    			{Haku_2D.ID, Haku_JD.ID},
    			{Haku_2A.ID, Haku_2A.ID},
    			{Haku_EA.ID, Haku_ActiveFlow.ID},
    			{Haku_6C.ID, Haku_FC.ID},
    			{Haku_Void.ID, Haku_J2A.ID}
    	};
    
    public final static AbstractCard[] CARDS_COPY = 
    	{
    			new Haku_3C().makeCopy(),
    			new Haku_6B().makeCopy(),
    			new Haku_Renka().makeCopy(),
    			new Haku_Zantetsu().makeCopy(),
    			new Haku_Enma().makeCopy(),
    			new Haku_JD().makeCopy(),
    			new Haku_2A().makeCopy(),
    			new Haku_EA().makeCopy(),
    			new Haku_6C().makeCopy(),
    			new Haku_Yomotsuhirasaka().makeCopy()
    	};
   
    public final static String[] CARDS_DESC = 
    	{	
    		"Guren / Sweep (recommended)", 
    		"Blitz Attack / Clash Assault", 
    		"Smart Steer / Renka", 
    		"Divide / Zantetsu", 
    		"Kishuu / Enma",
    		"Alpha Counter / Reject Guard",
    		"Claw Grip / Claw Grip",
    		"Exceed Accel / Active Flow",
    		"Command Order / Fatal Counter",
    		"Void / Crystal Wall"
    	};
    
    public final static float LABEL_START_X = 350.0f;
    public final static float LABEL_START_Y = 750.0f;

    public final static float CARDS_TOGGLE_START_X = 400.0f;
    public final static float CARDS_TOGGLE_START_Y = 700.0f;
    public final static float CARDS_SPACE_STEP = -40.0f;

    public final static float BOSSES_TOGGLE_START_X = 350.0f;
    public final static float BOSSES_TOGGLE_START_Y = 700.0F + (LIST_STARTING_CARDS.length+1)*CARDS_SPACE_STEP;
    public final static String TOGGLE_BOSSES_LABEL = "Enable custom bosses. (1 boss in progress, need to restart the game to take effect.) ";

    
}