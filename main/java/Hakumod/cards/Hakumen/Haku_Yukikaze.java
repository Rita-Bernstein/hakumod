package Hakumod.cards.Hakumen;

import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.actions.common.GainBlockAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.localization.CardStrings;
import com.megacrit.cardcrawl.monsters.AbstractMonster;

import Hakumod.action.NegateAction;
import Hakumod.action.ParryAction;
import Hakumod.action.UsingSpecialAction;
import Hakumod.patches.AbstractCardEnum;
import Hakumod.patches.CustomTags;
import Hakumod.powers.Haku_MagatamaPower;
import basemod.abstracts.CustomCard;
import basemod.helpers.CardTags;

public class Haku_Yukikaze extends CustomCard{
	public static final String ID = "Haku_Yukikaze";
	
	private static final CardStrings cardStrings = CardCrawlGame.languagePack.getCardStrings(ID);
	public static final String NAME = cardStrings.NAME;
	public static final String RAW_DESCRIPTION = cardStrings.DESCRIPTION;
	//public static final String UPG_DESCRIPTION = cardStrings.UPGRADE_DESCRIPTION;
	
	public static final String IMG_PATH = "Hakumod/img/cards/Haku_Yukikaze.png";
	
	private static final int COST = 1;
	
	private static final int ATTACK_DMG = 25;
	private static final int UPGRADE_PLUS_DMG = 5;
	
	private static final int BLOCK = 12;
	private static final int UPGRADE_PLUS_BLOCK = 3;
	
	private static int BUFF = 1;
	//private static int UPGRADE_BUFF = 1;
	 
	public final int MAGATAMA_COST = 4;
	
	public Haku_Yukikaze() {
		super(ID, NAME, IMG_PATH, COST, RAW_DESCRIPTION,
				AbstractCard.CardType.SKILL,
				AbstractCardEnum.HAKUMEN_COLOR,
				AbstractCard.CardRarity.UNCOMMON,
				AbstractCard.CardTarget.ENEMY);
		
		this.baseDamage = ATTACK_DMG;
		this.baseBlock = BLOCK;
		this.magicNumber = this.baseMagicNumber = BUFF;
		this.tags.add(CustomTags.SPECIAL);
		this.tags.add(CustomTags.PARRY);

	}
	
	public void use(AbstractPlayer p, AbstractMonster m) {
		if (new UsingSpecialAction(p, MAGATAMA_COST).canUseSpecialAction()) {
    		
			AbstractDungeon.actionManager.addToBottom( new UsingSpecialAction(p, MAGATAMA_COST));
			//AbstractDungeon.actionManager.addToBottom(new ParryAction(p, null, m, "block", baseBlock));
			AbstractDungeon.actionManager.addToBottom(new ParryAction(p, null, m, "attack", baseDamage	));
			AbstractDungeon.actionManager.addToBottom(new ParryAction(p, null, m, "buffer", magicNumber));
		}
	}
	
	public AbstractCard makeCopy() {
		return new Haku_Yukikaze();
	}
	
	public void upgrade() {
		if (!this.upgraded) {
			upgradeName();
			upgradeBlock(UPGRADE_PLUS_BLOCK);
			upgradeDamage(UPGRADE_PLUS_DMG);
			//upgradeMagicNumber(UPGRADE_BUFF);
			//initializeDescription();
		}
	}
}