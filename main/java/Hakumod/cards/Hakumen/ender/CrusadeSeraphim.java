package Hakumod.cards.Hakumen.ender;

import Hakumod.action.OkizemeAction;
import Hakumod.action.StarterAction;
import Hakumod.action.UtilsApplyEffect;
import Hakumod.cards.Hakumen.abstracts.HakuCustomCard;
import Hakumod.patches.AbstractCardEnum;
import Hakumod.patches.CustomTags;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.localization.CardStrings;
import com.megacrit.cardcrawl.monsters.AbstractMonster;

public class CrusadeSeraphim extends HakuCustomCard {

	public static final String ID = makeCardID(CrusadeSeraphim.class.getSimpleName());
	private static final CardStrings cardStrings = CardCrawlGame.languagePack.getCardStrings(ID);
	public static final String NAME = cardStrings.NAME;
	public static final String RAW_DESCRIPTION = cardStrings.DESCRIPTION;

	private static final int COST = 1;
	private static final int ATTACK_DMG = 8;
	private static final int UPGRADE_PLUS_DMG = 3;
	private static final int BUFF = 1;
	//private static final int UPGRADE_BUFF = 1;

	public CrusadeSeraphim() {
		super(ID, NAME, COST, RAW_DESCRIPTION,
				AbstractCard.CardType.ATTACK,
				AbstractCardEnum.HAKUMEN_COLOR,
				AbstractCard.CardRarity.COMMON,
				AbstractCard.CardTarget.ENEMY);
		this.baseDamage = ATTACK_DMG;
		this.magicNumber  = this.baseMagicNumber = BUFF;
		this.tags.add(CustomTags.STARTER);
		this.tags.add(CustomTags.ENDER);
	}

	@Override
	public void upgrade() {
		if (!this.upgraded) {
			upgradeName();
			upgradeDamage(UPGRADE_PLUS_DMG);
			//upgradeMagicNumber(UPGRADE_BUFF);
		}
	}

    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {
    	damage(m, p, this.damage, this.damageTypeForTurn, AbstractGameAction.AttackEffect.SLASH_DIAGONAL);

    	act(new StarterAction(p, this, m, UtilsApplyEffect.DEFENSE, this.magicNumber));
    	
    	act(new OkizemeAction(p, this, m, UtilsApplyEffect.DEFENSE, this.magicNumber));
    	
    }
	
	public AbstractCard makeCopy() {
		return new CrusadeSeraphim();
	}
	
	
}