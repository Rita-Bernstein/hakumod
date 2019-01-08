package Hakumod.patches;

import java.util.ArrayList;

import com.evacipated.cardcrawl.modthespire.lib.SpireInsertPatch;
import com.evacipated.cardcrawl.modthespire.lib.SpirePatch;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;

import Hakumod.cards.Hakumen.Haku_3C;
import Hakumod.characters.Hakumen;

@SpirePatch(cls="com.megacrit.cardcrawl.events.shrines.GremlinMatchGame", method="initializeCards")
public class GremlinMatchPatch {
	@SpireInsertPatch(rloc=32, localvars={"retVal"})
	public static void Insert(Object __obj_instance, ArrayList<AbstractCard> retVal) {
		if (AbstractDungeon.player instanceof Hakumen) {
			retVal.add(new Haku_3C());
		}
	}
	
}
