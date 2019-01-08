package Hakumod.patches;

import com.evacipated.cardcrawl.modthespire.lib.SpireEnum;
import com.megacrit.cardcrawl.cards.AbstractCard;

import basemod.helpers.CardTags;

public class CustomTags
{
	@SpireEnum public static AbstractCard.CardTags STARTER;
	@SpireEnum public static AbstractCard.CardTags COMBO;
	@SpireEnum public static AbstractCard.CardTags ENDER;
	
	@SpireEnum public static AbstractCard.CardTags SPECIAL;
	
	@SpireEnum public static AbstractCard.CardTags PARRY;
	@SpireEnum public static AbstractCard.CardTags AIR;
}