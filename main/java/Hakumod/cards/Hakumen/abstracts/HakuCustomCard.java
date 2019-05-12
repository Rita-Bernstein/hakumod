package Hakumod.cards.Hakumen.abstracts;

import Hakumod.utils.AssetsPath;
import basemod.abstracts.CustomCard;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.actions.common.DamageAction;
import com.megacrit.cardcrawl.actions.common.GainBlockAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.core.Settings;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.helpers.TipHelper;
import com.megacrit.cardcrawl.powers.AbstractPower;

public abstract class HakuCustomCard extends CustomCard {

    private AbstractCard cardToPreview = null;
    private boolean isHovered = false;

    private static final float CARD_TIP_PAD = 16.0F;
    private static final float RESIZE = 1.5F;

    public HakuCustomCard(String id, String name, int cost, String rawDescription, CardType type,
                          CardColor color, CardRarity rarity, CardTarget target) {
        super(id, name, makeCardPath(id), cost, rawDescription, type, color, rarity, target);
        this.cardToPreview = null;
    }

    public HakuCustomCard(String id, String name, int cost, String rawDescription, CardType type,
                          CardColor color, CardRarity rarity, CardTarget target, AbstractCard cardToPreview) {
        super(id, name, makeCardPath(id), cost, rawDescription, type, color, rarity, target);
        this.cardToPreview = cardToPreview;
    }


    public void renderCardTip(SpriteBatch sb){

        if (!Settings.hideCards && this.isHovered && this.cardToPreview != null){
            TipHelper.renderTipForCard(this, sb, this.keywords);

            float tmpScale = this.drawScale / RESIZE;

            if ((AbstractDungeon.player != null) && (AbstractDungeon.player.isDraggingCard)) {
                return;
            }

            float offsetX = (((AbstractCard.IMG_WIDTH / 2.0F) + ((AbstractCard.IMG_WIDTH / 2.0F) / RESIZE) + (CARD_TIP_PAD)) * this.drawScale);
            float offsetY = ((AbstractCard.IMG_HEIGHT / 2.0F) - (AbstractCard.IMG_HEIGHT / 2.0F / RESIZE)) * this.drawScale;

            if (this.current_x > Settings.WIDTH * 0.75F) {
                this.cardToPreview.current_x = this.current_x + offsetX ;
            } else {
                this.cardToPreview.current_x = this.current_x - offsetX;
            }

            this.cardToPreview.current_y = this.current_y + offsetY;
            this.cardToPreview.drawScale = tmpScale;
            if (this.upgraded) {this.cardToPreview.upgrade();}
            this.cardToPreview.render(sb);
        }

        else {
            super.renderCardTip(sb);
        }
    }

    public void hover(){
        super.hover();
        this.isHovered = true;
    }

    public void unhover() {
        super.unhover();
        this.isHovered = false;
    }

    public void act(AbstractGameAction action){
        AbstractDungeon.actionManager.addToBottom(action);
    }

    public void damage(AbstractCreature target, AbstractCreature source, int damage, DamageInfo.DamageType damageType, AbstractGameAction.AttackEffect attackEffect){
        act(new DamageAction(target, new DamageInfo(source, damage, damageType), attackEffect));
    }

    public void block(AbstractCreature target, AbstractCreature source, int block){
        act(new GainBlockAction(target, source, block));
    }

    public void power(AbstractCreature target, AbstractCreature source, AbstractPower power, int amount){
        act(new ApplyPowerAction(target, source, power, amount));
    }

    protected static String makeCardID(String cardName){return AssetsPath.makeID(cardName);}

    private static String makeCardPath(String cardID){
	String IDPath = AssetsPath.makeCardPath(cardID);
        return IDPath.replace(AssetsPath.MOD_ID, "");
    }

}
