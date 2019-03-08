package Hakumod.cards.Hakumen;

import Hakumod.action.UsingSpecialAction;
import Hakumod.cards.Hakumen.Utils.Haku_Special;
import Hakumod.patches.AbstractCardEnum;
import Hakumod.patches.CustomTags;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.localization.CardStrings;
import com.megacrit.cardcrawl.monsters.AbstractMonster;

//import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
//import com.megacrit.cardcrawl.actions.common.DamageAction;
//import com.megacrit.cardcrawl.localization.CardStrings;


public class Haku_Tsubaki extends Haku_Special {

	public static final String ID = "Haku_Tsubaki";
	
	private static final CardStrings cardStrings = CardCrawlGame.languagePack.getCardStrings(ID);
	public static final String NAME = cardStrings.NAME;
	public static final String RAW_DESCRIPTION = cardStrings.DESCRIPTION;
	public static final String UPG_DESCRIPTION = cardStrings.UPGRADE_DESCRIPTION;
	
	public static final String IMG_PATH = "Hakumod/img/cards/Haku_Tsubaki.png";
	private static final int COST = 0;
	private static final int ATTACK_DMG = 20;
	private static final int UPGRADE_PLUS_DMG = 4;
	private static int MAGATAMA_COST = 3;
	
	public Haku_Tsubaki() {
		super(ID, NAME, IMG_PATH, COST, RAW_DESCRIPTION, 
				AbstractCard.CardType.ATTACK, 
				AbstractCardEnum.HAKUMEN_COLOR, 
				AbstractCard.CardRarity.UNCOMMON, 
				AbstractCard.CardTarget.ENEMY, 
				MAGATAMA_COST);
		// TODO Auto-generated constructor stub
		this.baseDamage = ATTACK_DMG;
		this.tags.add(CustomTags.SPECIAL);
		this.tags.add(CustomTags.AIR);
		this.exhaustOnUseOnce = true;
	}

	@Override
	public void upgrade() {
		// TODO Auto-generated method stub
		if (!this.upgraded) {
			upgradeName();
			upgradeDamage(UPGRADE_PLUS_DMG);
			//upgradeMagicNumber(UPGRADE_BUFF);
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
    	
    	AbstractCard cardToAdd = new Haku_Tsubaki().makeCopy();
    	if (this.upgraded) {cardToAdd.upgrade();}
    	p.drawPile.addToTop(cardToAdd);
	
    }
	
	public AbstractCard makeCopy() {
		return new Haku_Tsubaki();
	}
	
	
}