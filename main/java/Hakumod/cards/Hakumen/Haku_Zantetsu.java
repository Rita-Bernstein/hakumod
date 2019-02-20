package Hakumod.cards.Hakumen;

import com.megacrit.cardcrawl.actions.AbstractGameAction;
//import com.megacrit.cardcrawl.actions.common.DrawCardAction;
import com.megacrit.cardcrawl.actions.common.GainEnergyAction;
//import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
//import com.megacrit.cardcrawl.actions.common.DamageAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.localization.CardStrings;
//import com.megacrit.cardcrawl.localization.CardStrings;
import com.megacrit.cardcrawl.monsters.AbstractMonster;

//import Hakumod.action.ComboAction;
import Hakumod.action.UsingSpecialAction;
import Hakumod.patches.AbstractCardEnum;
import Hakumod.patches.CustomTags;

public class Haku_Zantetsu extends Haku_Special{

	public static final String ID = "Haku_Zantetsu";
	
	private static final CardStrings cardStrings = CardCrawlGame.languagePack.getCardStrings(ID);
	public static final String NAME = cardStrings.NAME;
	public static final String RAW_DESCRIPTION = cardStrings.DESCRIPTION;
	public static final String UPG_DESCRIPTION = cardStrings.UPGRADE_DESCRIPTION;
	
	public static final String IMG_PATH = "Hakumod/img/cards/Haku_Zantetsu.png";
	private static final int COST = 1;
	private static final int ATTACK_DMG = 18;
	private static final int UPGRADE_PLUS_DMG = 3;
	
	public final static int MAGATAMA_COST = 3;
	public static final int ENERGY_BONUS = 1;
	public static final int UPGRADE_ENERGY_BONUS = 1;
	
	public Haku_Zantetsu() {
		super(ID, NAME, IMG_PATH, COST, RAW_DESCRIPTION, 
				AbstractCard.CardType.ATTACK,
				AbstractCardEnum.HAKUMEN_COLOR, 
				AbstractCard.CardRarity.COMMON,
				AbstractCard.CardTarget.ENEMY,
				MAGATAMA_COST);
		// TODO Auto-generated constructor stub
		this.baseDamage = ATTACK_DMG;
		this.magicNumber = this.baseMagicNumber = ENERGY_BONUS;
		this.tags.add(CustomTags.SPECIAL);
	}

	@Override
	public void upgrade() {
		// TODO Auto-generated method stub
		if (!this.upgraded) {
			upgradeName();
			upgradeDamage(UPGRADE_PLUS_DMG);
			upgradeMagicNumber(UPGRADE_ENERGY_BONUS);
			this.rawDescription = UPG_DESCRIPTION;
			initializeDescription();
		}
	}

    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {
    		
		AbstractDungeon.actionManager.addToBottom( new UsingSpecialAction(p, MAGATAMA_COST));
		
    	AbstractDungeon.actionManager.addToBottom(new com.megacrit.cardcrawl.actions.common.DamageAction(m,
				new DamageInfo(p, this.damage, this.damageTypeForTurn),
				AbstractGameAction.AttackEffect.SLASH_HEAVY));
    	
    	AbstractDungeon.actionManager.addToBottom(new GainEnergyAction(this.magicNumber));
    }
	
	public AbstractCard makeCopy() {
		return new Haku_Zantetsu();
	}
	
	
	
}