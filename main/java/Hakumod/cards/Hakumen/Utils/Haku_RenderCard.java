package Hakumod.cards.Hakumen.Utils;

import basemod.abstracts.CustomCard;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.Settings;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.helpers.TipHelper;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.screens.SingleCardViewPopup;

public class Haku_RenderCard extends CustomCard {

    private AbstractCard cardToPreview = null;
    private boolean isHovered = false;

    private static final float CARD_TIP_PAD = 16.0F;
    private static final float RESIZE = 1.5F;

    public Haku_RenderCard(String id, String name, String img, int cost, String rawDescription, CardType type,
                           CardColor color, CardRarity rarity, CardTarget target) {
        super(id, name, img, cost, rawDescription, type, color, rarity, target);
        this.cardToPreview = null;
    }

    public Haku_RenderCard(String id, String name, String img, int cost, String rawDescription, CardType type,
                        CardColor color, CardRarity rarity, CardTarget target, AbstractCard cardToPreview) {
        super(id, name, img, cost, rawDescription, type, color, rarity, target);
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
            this.cardToPreview.render(sb);
        }

        else {
            super.renderCardTip(sb);
        }
    }

    /*@Override
    public void renderInLibrary(SpriteBatch sb)
    {
        if (!(this.current_y >= -200.0F * Settings.scale) && (this.current_y <= Settings.HEIGHT + 200.0F * Settings.scale)) {
            return;
        }
        if ((this.isSeen) && (!this.isLocked))
        {
           // AbstractCard copy = makeStatEquivalentCopy();
            cardToPreview.current_x = this.current_x;
            cardToPreview.current_y = this.current_y;
            cardToPreview.drawScale = this.drawScale;
            //copy.upgrade();
            cardToPreview.displayUpgrades();
            cardToPreview.render(sb);
        }
        else
        {
            super.renderInLibrary(sb);
        }
    }*/


    public void hover(){
        super.hover();
        this.isHovered = true;
    }

    public void unhover() {
        super.unhover();
        this.isHovered = false;
    }

    @Override
    public void upgrade() {

    }

    @Override
    public void use(AbstractPlayer arg0, AbstractMonster arg1) {

    }
}
