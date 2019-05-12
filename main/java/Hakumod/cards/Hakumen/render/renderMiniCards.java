package Hakumod.cards.Hakumen.render;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.core.Settings;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.helpers.TipHelper;

public class renderMiniCards {
    private boolean isHovered;
    private AbstractCard card;
    private AbstractCard cardToPreview;

    private static final float CARD_TIP_PAD = 16.0F;
    private static final float RESIZE = 1.5F;


    public renderMiniCards(AbstractCard card, AbstractCard cardToPreview) {
        this.card = card;
        this.cardToPreview = cardToPreview;
        this.isHovered = false;
    }

    public void renderCardTip(SpriteBatch sb){

        if (!Settings.hideCards && this.isHovered){
            TipHelper.renderTipForCard(card, sb, this.card.keywords);
            float tmpScale = this.card.drawScale / RESIZE;

            if ((AbstractDungeon.player != null) && (AbstractDungeon.player.isDraggingCard)) {
                return;
            }

            float offsetX = (((AbstractCard.IMG_WIDTH / 2.0F) + ((AbstractCard.IMG_WIDTH / 2.0F) / RESIZE) + (CARD_TIP_PAD)) * card.drawScale);
            float offsetY = ((AbstractCard.IMG_HEIGHT / 2.0F) - (AbstractCard.IMG_HEIGHT / 2.0F / RESIZE)) * card.drawScale;

            if (card.current_x > Settings.WIDTH * 0.75F) {
                this.cardToPreview.current_x = card.current_x + offsetX ;
            } else {
                this.cardToPreview.current_x = card.current_x - offsetX;
            }

            this.cardToPreview.current_y = card.current_y + offsetY;
            this.cardToPreview.drawScale = tmpScale;
            this.cardToPreview.render(sb);

        }

    }

    public void hover(){
        this.isHovered = true;
    }

    public void unhover() {
        //card.unhover();
        this.isHovered = false;
        //this.cardToPreview = null;
    }

}
