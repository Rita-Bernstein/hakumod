package Hakumod.cards.Hakumen;

import Hakumod.patches.AbstractCardEnum;
import basemod.abstracts.CustomCard;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.localization.CardStrings;
import com.megacrit.cardcrawl.monsters.AbstractMonster;

//import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
//import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
//import com.megacrit.cardcrawl.actions.common.DamageAction;
//import com.megacrit.cardcrawl.localization.CardStrings;
//import Hakumod.powers.MagatamaPower;
//import Hakumod.powers.MagatamaPower;
//import basemod.helpers.BaseModTags;
//import basemod.helpers.CardTags;


public class Haku_Thin extends CustomCard{

    public static final String ID = "Haku_Thin";

    private static final CardStrings cardStrings = CardCrawlGame.languagePack.getCardStrings(ID);
    public static final String NAME = cardStrings.NAME;
    public static final String RAW_DESCRIPTION = cardStrings.DESCRIPTION;
    //public static final String UPG_DESCRIPTION = cardStrings.UPGRADE_DESCRIPTION;

    public static final String IMG_PATH = "Hakumod/img/cards/Haku_Thin.png";
    private static final int COST = 2;
    //private static final int UPGRADED_COST = 2;
    private static final int ATTACK_DMG = 5;
    
    private static int BONUS_DISCARD = 1;
    private static int BONUS_EXHAUSTED = 2;
    private static int UPG_BONUS_EXHAUSTED = 1;
    
    //private static int UPGRADED_BUFF = 1;

    public Haku_Thin() {
        super(ID, NAME, IMG_PATH, COST, RAW_DESCRIPTION,
                AbstractCard.CardType.ATTACK,
                AbstractCardEnum.HAKUMEN_COLOR,
                AbstractCard.CardRarity.UNCOMMON,
                AbstractCard.CardTarget.ENEMY);
        // TODO Auto-generated constructor stub
        this.baseDamage = ATTACK_DMG;
        this.magicNumber = this.baseMagicNumber = BONUS_EXHAUSTED;
    }

    @Override
    public void upgrade() {
        // TODO Auto-generated method stub
        if (!this.upgraded) {
            upgradeName();
            upgradeMagicNumber(UPG_BONUS_EXHAUSTED);
            //upgradeBaseCost(UPGRADED_COST);
            //this.rawDescription = UPG_DESCRIPTION;
            initializeDescription();
        }
    }
    
    public int getDamage() {
    	return AbstractDungeon.player.discardPile.size() * BONUS_DISCARD + AbstractDungeon.player.exhaustPile.size() * this.magicNumber + this.baseDamage;
    }
  //Display damage when the card is in the hand.
  	@Override
  	public void applyPowers() {
  		this.damage = getDamage();
  		if (this.damage > 0) {this.isDamageModified = true;}
  	}
  	
  	/* (non-Javadoc)
  	 * @see com.megacrit.cardcrawl.cards.AbstractCard#calculateDamageDisplay(com.megacrit.cardcrawl.monsters.AbstractMonster)
  	 */
  	@Override
  	public void calculateDamageDisplay(AbstractMonster mo) {
  		// TODO Auto-generated method stub
  		calculateCardDamage(mo);
  	}	

      /* (non-Javadoc)
  	 * @see com.megacrit.cardcrawl.cards.AbstractCard#calculateCardDamage(com.megacrit.cardcrawl.monsters.AbstractMonster)
  	 */
  	//Display damage when the card is selected.
  	@Override
  	public void calculateCardDamage(AbstractMonster mo) {
  		this.damage = getDamage();
  		if (this.damage > 0) {this.isDamageModified = true;}
  	}

    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {
    	
    	int totalDamage = getDamage();
    	AbstractDungeon.actionManager.addToBottom(new com.megacrit.cardcrawl.actions.common.DamageAction(m,
				new DamageInfo(p, totalDamage, this.damageTypeForTurn),
				AbstractGameAction.AttackEffect.SLASH_HEAVY));
    }

    public AbstractCard makeCopy() {
        return new Haku_Thin();
    }
}