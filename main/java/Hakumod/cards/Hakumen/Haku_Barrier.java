package Hakumod.cards.Hakumen;

import Hakumod.cards.Hakumen.Utils.Haku_CustomCard;
import Hakumod.patches.AbstractCardEnum;
import basemod.abstracts.CustomCard;
import com.megacrit.cardcrawl.actions.common.GainBlockAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.localization.CardStrings;
import com.megacrit.cardcrawl.monsters.AbstractMonster;

public class Haku_Barrier extends Haku_CustomCard {
	public static final String ID = "Haku_Barrier";
	
	private static final CardStrings cardStrings = CardCrawlGame.languagePack.getCardStrings(ID);
	public static final String NAME = cardStrings.NAME;
	public static final String RAW_DESCRIPTION = cardStrings.DESCRIPTION;
	//public static final String UPG_DESCRIPTION = cardStrings.UPGRADE_DESCRIPTION;
	
	public static final String IMG_PATH = "Hakumod/img/cards/Haku_Barrier.png";
	
	private static final int COST = 1;
	private static final int BLOCK = 3;
	private static final int UPGRADE_PLUS_BLOCK = 3;
	
	private static int BUFF = 3;
	private static int UPGRADE_BUFF = 2;
	 
	
	public Haku_Barrier() {
		super(ID, NAME, IMG_PATH, COST, RAW_DESCRIPTION,
				AbstractCard.CardType.SKILL,
				AbstractCardEnum.HAKUMEN_COLOR,
				AbstractCard.CardRarity.COMMON,
				AbstractCard.CardTarget.SELF);

		this.baseBlock = BLOCK;
		this.magicNumber = this.baseMagicNumber = BUFF;
		//CardTags.addTags(this, CustomTags.PARRY);
	}
	
	public void use(AbstractPlayer p, AbstractMonster m) {
		//AbstractDungeon.actionManager.addToBottom(new NegateAction(p, null, m, "defense", this.magicNumber));
		/*AbstractDungeon.actionManager.addToBottom(new ApplyPowerAction(p, p,
				new BlurPower(p, this.magicNumber), this.magicNumber));*/
		
		/*int amountBlocked = 0;
		for (AbstractMonster mo : AbstractDungeon.getCurrRoom().monsters.monsters) {
			if (!mo.isDead) {
				amountBlocked += this.block;
			}
		}*/

		AbstractDungeon.actionManager.addToBottom(new GainBlockAction(p, p, this.block));
		
	}

	public int getBlock() {
		return this.baseBlock + AbstractDungeon.player.cardsPlayedThisTurn;
	}
	/* (non-Javadoc)
	 * @see com.megacrit.cardcrawl.cards.AbstractCard#applyPowers()
	 */
	@Override
	public void applyPowers() {
		// TODO Auto-generated method stub
		this.block = getBlock();
		if (this.block > 0) {this.isBlockModified = true;}
	}

	/* (non-Javadoc)
	 * @see com.megacrit.cardcrawl.cards.AbstractCard#calculateCardDamage(com.megacrit.cardcrawl.monsters.AbstractMonster)
	 */
	@Override
	public void calculateCardDamage(AbstractMonster arg0) {
		// TODO Auto-generated method stub
		this.block = getBlock();
		if (this.block > 0) {this.isBlockModified = true;}
		
	}

	/* (non-Javadoc)
	 * @see com.megacrit.cardcrawl.cards.AbstractCard#calculateDamageDisplay(com.megacrit.cardcrawl.monsters.AbstractMonster)
	 */
	@Override
	public void calculateDamageDisplay(AbstractMonster mo) {
		// TODO Auto-generated method stub
		calculateDamageDisplay(mo);
	}

	public AbstractCard makeCopy() {
		return new Haku_Barrier();
	}
	
	public void upgrade() {
		if (!this.upgraded) {
			upgradeName();
			upgradeBlock(UPGRADE_PLUS_BLOCK);
			//this.rawDescription = UPG_DESCRIPTION;
			upgradeMagicNumber(UPGRADE_BUFF);
			initializeDescription();
		}
	}
}
