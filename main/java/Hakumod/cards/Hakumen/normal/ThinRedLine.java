package Hakumod.cards.Hakumen.normal;

import Hakumod.cards.Hakumen.abstracts.HakuCustomCard;
import Hakumod.patches.AbstractCardEnum;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.DamageAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.localization.CardStrings;
import com.megacrit.cardcrawl.monsters.AbstractMonster;

public class ThinRedLine extends HakuCustomCard {

    public static final String ID = makeCardID(ThinRedLine.class.getSimpleName());
    private static final CardStrings cardStrings = CardCrawlGame.languagePack.getCardStrings(ID);
    public static final String NAME = cardStrings.NAME;
    public static final String RAW_DESCRIPTION = cardStrings.DESCRIPTION;

    private static final int COST = 2;
    private static final int ATTACK_DMG = 5;
    
    private static final int BONUS_DISCARD = 1;
    private static final int BONUS_EXHAUSTED = 2;
    private static final int UPG_BONUS_EXHAUSTED = 1;

    public ThinRedLine() {
        super(ID, NAME, COST, RAW_DESCRIPTION,
                AbstractCard.CardType.ATTACK,
                AbstractCardEnum.HAKUMEN_COLOR,
                AbstractCard.CardRarity.UNCOMMON,
                AbstractCard.CardTarget.ENEMY);
        this.baseDamage = ATTACK_DMG;
        this.magicNumber = this.baseMagicNumber = BONUS_EXHAUSTED;
    }

    @Override
    public void upgrade() {
        if (!this.upgraded) {
            upgradeName();
            upgradeMagicNumber(UPG_BONUS_EXHAUSTED);
            initializeDescription();
        }
    }
    
    public int getDamage() {
    	return AbstractDungeon.player.discardPile.size() * BONUS_DISCARD + AbstractDungeon.player.exhaustPile.size() * this.magicNumber + ATTACK_DMG;
    }

  	@Override
  	public void applyPowers() {
  		this.baseDamage = getDamage();
  		super.applyPowers();
  	}
  	
  	@Override
  	public void calculateDamageDisplay(AbstractMonster mo) {
        calculateCardDamage(mo);
        super.calculateDamageDisplay(mo);
  	}	

    @Override
  	public void calculateCardDamage(AbstractMonster mo) {
  		this.baseDamage = getDamage();
  		super.calculateCardDamage(mo);
  	}

    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {
    	int totalDamage = getDamage();
    	act(new DamageAction(m,
				new DamageInfo(p, totalDamage, this.damageTypeForTurn),
				AbstractGameAction.AttackEffect.SLASH_HEAVY));
    }

    public AbstractCard makeCopy() {
        return new ThinRedLine();
    }
}