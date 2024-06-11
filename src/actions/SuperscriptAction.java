package actions;

import javax.swing.text.StyleConstants;
import javax.swing.text.StyledEditorKit;
import java.awt.event.ActionEvent;

public class SuperscriptAction extends StyledEditorKit.StyledTextAction{

    public SuperscriptAction() {
        super(StyleConstants.Superscript);
    }


    @Override
    public void actionPerformed(ActionEvent e) {
        StyleConstants.setSuperscript(SimpleAttributeSet, !StyleConstants.isSuperscript(mutableAttributeSet));
    }
}
