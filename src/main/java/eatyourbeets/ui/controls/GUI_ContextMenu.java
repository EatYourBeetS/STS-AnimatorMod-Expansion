package eatyourbeets.ui.controls;

import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import eatyourbeets.interfaces.delegates.ActionT1;
import eatyourbeets.interfaces.delegates.FuncT1;
import eatyourbeets.ui.hitboxes.AdvancedHitbox;

import java.util.ArrayList;
import java.util.List;


// TODO this is a variant of GUI_Dropdown that does not have a button heading it. This can be used to show a pop-up menu when the user right-clicks some object
public class GUI_ContextMenu<T> extends GUI_Dropdown<T>
{
    public GUI_ContextMenu(AdvancedHitbox hb) {
        super(hb);
        Initialize();
    }

    public GUI_ContextMenu(AdvancedHitbox hb, FuncT1<String, T> labelFunction) {
        super(hb, labelFunction);
        Initialize();
    }

    public GUI_ContextMenu(AdvancedHitbox hb, FuncT1<String, T> labelFunction, ArrayList<T> options) {
        super(hb, labelFunction, options);
        Initialize();
    }

    public GUI_ContextMenu(AdvancedHitbox hb, FuncT1<String, T> labelFunction, ArrayList<T> options, BitmapFont font, int maxRows, boolean canAutosizeButton) {
        super(hb, labelFunction, options, font, maxRows, canAutosizeButton);
        Initialize();
    }

    protected void Initialize() {
        this.button.SetActive(false);
    }

    public GUI_ContextMenu<T> SetCanAutosizeButton(boolean value) {
        super.SetCanAutosizeButton(value);
        return this;
    }

    public GUI_ContextMenu<T> SetOnChange(ActionT1<List<T>> onChange) {
        super.SetOnChange(onChange);
        return this;
    }

    public GUI_ContextMenu<T> SetOnOpenOrClose(ActionT1<Boolean> onOpenOrClose) {
        super.SetOnOpenOrClose(onOpenOrClose);
        return this;
    }

    public GUI_ContextMenu<T> SetPosition(float x, float y) {
        super.SetPosition(x, y);
        return this;
    }

    @Override
    protected void RenderArrows(SpriteBatch sb) {
    }

}
