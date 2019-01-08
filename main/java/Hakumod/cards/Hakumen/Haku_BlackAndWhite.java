package Hakumod.cards.Hakumen;

import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.actions.defect.ChannelAction;
//import com.megacrit.cardcrawl.actions.common.DamageAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.localization.CardStrings;
//import com.megacrit.cardcrawl.localization.CardStrings;
import com.megacrit.cardcrawl.monsters.AbstractMonster;

import Hakumod.action.OkizemeAction;
import Hakumod.action.StarterAction;
import Hakumod.orbs.VoidOrb;
import Hakumod.patches.AbstractCardEnum;
import Hakumod.patches.CustomTags;
import Hakumod.powers.Haku_MagatamaPower;
import basemod.abstracts.CustomCard;
//import basemod.helpers.BaseModTags;
//import basemod.helpers.CardTags;

public class Haku_BlackAndWhite extends CustomCard{

	public static final String ID = "Haku_BlackAndWhite";
	
	private static final CardStrings cardStrings = CardCrawlGame.languagePack.getCardStrings(ID);
	public static final String NAME = cardStrings.NAME;
	public static final String RAW_DESCRIPTION = cardStrings.DESCRIPTION;
	public static final String UPG_DESCRIPTION = cardStrings.UPGRADE_DESCRIPTION;
	
	public static final String IMG_PATH = "Hakumod/img/cards/Haku_BlackAndWhite.png";
	private static final int COST = 1;
	private static final int ATTACK_DMG = 6;
	//private static final int UPGRADE_PLUS_DMG = 2;
	private static int DAMAGE_BOOST = 1;
	private static int UPGRADED_DAMAGE_BOOST = 1;
	    
	public Haku_BlackAndWhite() {
		super(ID, NAME, IMG_PATH, COST, RAW_DESCRIPTION, 
				AbstractCard.CardType.ATTACK,
				AbstractCardEnum.HAKUMEN_COLOR,
				AbstractCard.CardRarity.COMMON,
				AbstractCard.CardTarget.ENEMY);
		// TODO Auto-generated constructor stub
		this.baseDamage = ATTACK_DMG;
		this.magicNumber = this.baseMagicNumber = DAMAGE_BOOST;
	}

	@Override
	public void upgrade() {
		// TODO Auto-generated method stub
		if (!this.upgraded) {
			upgradeName();
			//upgradeDamage(UPGRADE_PLUS_DMG);
			upgradeMagicNumber(UPGRADED_DAMAGE_BOOST);
			this.rawDescription = UPG_DESCRIPTION;
			initializeDescription();
		}
	}

	public int isBlackAndWhite(AbstractCard c) {
		if (c.hasTag(CustomTags.PARRY)) {
			return 1;
		}
		return 0;
	}
	
    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {
    	
    	int damageBoost = 0;

 
		for (AbstractCard c : AbstractDungeon.player.discardPile.group) {
			damageBoost += (isBlackAndWhite(c) * this.magicNumber);	
		}
    	
    	
		for (AbstractCard c : AbstractDungeon.player.hand.group) {
			damageBoost += (isBlackAndWhite(c) * this.magicNumber);
		}
    	
     	AbstractDungeon.actionManager.addToBottom(new com.megacrit.cardcrawl.actions.common.DamageAction(m,
				new DamageInfo(p, this.damage + damageBoost, this.damageTypeForTurn),
				AbstractGameAction.AttackEffect.BLUNT_HEAVY));
    }
	
	public AbstractCard makeCopy() {
		return new Haku_BlackAndWhite();
	}
	
}
