package actions;

import javax.swing.text.StyleConstants;
import javax.swing.text.StyledEditorKit;
import java.awt.event.ActionEvent;

public class SubscriptAction extends StyledEditorKit.StyledTextAction{
    public SubscriptAction() {
        super(StyleConstants.Subscript);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        StyleConstants.setSubscript(SimpleAttributeSet, !StyleConstants.isSubscript(mutableAttributeSet));
    }
}
