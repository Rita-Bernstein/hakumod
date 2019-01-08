package Hakumod.cards.Hakumen;

import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.actions.common.DrawCardAction;
import com.megacrit.cardcrawl.actions.unique.LoseEnergyAction;
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
import com.megacrit.cardcrawl.powers.StrengthPower;
import com.megacrit.cardcrawl.ui.panels.EnergyPanel;

import Hakumod.action.AddToHandAction;
import Hakumod.action.ComboAction;
import Hakumod.patches.AbstractCardEnum;
import Hakumod.patches.CustomTags;
import Hakumod.powers.Haku_MagatamaPower;
import Hakumod.powers.Haku_OffensePower;
//import Hakumod.powers.MagatamaPower;
import basemod.abstracts.CustomCard;
//import basemod.helpers.BaseModTags;
import basemod.helpers.CardTags;


public class Haku_6C extends CustomCard{

	public static final String ID = "Haku_6C";
	
	private static final CardStrings cardStrings = CardCrawlGame.languagePack.getCardStrings(ID);
	public static final String NAME = cardStrings.NAME;
	public static final String RAW_DESCRIPTION = cardStrings.DESCRIPTION;
	//public static final String UPG_DESCRIPTION = cardStrings.UPGRADE_DESCRIPTION;
	public static final String[] EXT_DESCRIPTION = cardStrings.EXTENDED_DESCRIPTION;
	
	public static final String IMG_PATH = "Hakumod/img/cards/Haku_6C.png";
	private static final int COST = 2;
	private static final int ATTACK_DMG = 10;
	private static final int UPGRADE_PLUS_DMG = 3;
	
	private static int BUFF = 2;
	private static int UPGRADE_DEBUFF = 1;
	
	public Haku_6C() {
		super(ID, NAME, IMG_PATH, COST, RAW_DESCRIPTION, 
				AbstractCard.CardType.ATTACK,
				AbstractCardEnum.HAKUMEN_COLOR,
				AbstractCard.CardRarity.UNCOMMON,
				AbstractCard.CardTarget.ENEMY);
		// TODO Auto-generated constructor stub
		this.baseDamage = ATTACK_DMG;
		this.magicNumber = this.baseMagicNumber = BUFF;
	}

	/* (non-Javadoc)
	 * @see com.megacrit.cardcrawl.cards.AbstractCard#canUpgrade()
	 */
	@Override
	public boolean canUpgrade() {
		// TODO Auto-generated method stub
		return true;
	}

	@Override
	public void upgrade() {
		// TODO Auto-generated method stub
		//if (!this.upgraded) {
		upgradeName();
		upgradeDamage(UPGRADE_PLUS_DMG);
		upgradeBaseCost(1);
			//upgradeMagicNumber(UPGRADE_DEBUFF);
		this.rawDescription = EXT_DESCRIPTION[Math.min(this.timesUpgraded-1,4)];
			//initializeDescription();
	}

    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {
    	
		
		AbstractDungeon.actionManager.addToBottom(new com.megacrit.cardcrawl.actions.common.DamageAction(m,
				new DamageInfo(p, this.damage, this.damageTypeForTurn),
				AbstractGameAction.AttackEffect.SLASH_HEAVY));
    	
		if (this.timesUpgraded >= 2) {
			AbstractDungeon.actionManager.addToBottom(
					new DrawCardAction(AbstractDungeon.player, this.magicNumber));
	 
		}
		if (this.timesUpgraded >= 3) {
			AbstractDungeon.actionManager.addToBottom(new ApplyPowerAction(p, p,
					new StrengthPower(p, this.magicNumber), this.magicNumber));
		}
		
		if (this.timesUpgraded >= 4) {
			for (AbstractCard cardInHand: p.hand.group) {
				cardInHand.upgrade();
			}
		}
		
		/*if (this.timesUpgraded >= 5) {
			AbstractCard cardMugen = new Haku_Mugen();
			cardMugen.setCostForTurn(0);
			cardMugen.exhaustOnUseOnce = true;
			p.useCard(cardMugen, m, 0);
		}*/
    }
	
	public AbstractCard makeCopy() {
		return new Haku_6C();
	}
	
	
}