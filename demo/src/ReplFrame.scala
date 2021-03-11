import java.awt.{BorderLayout, ComponentOrientation, Container, Insets}
import javax.swing.{JFrame, JPanel, JTextArea, JTextField}

class ReplFrame extends JFrame {
  val inputField = new JTextField()
  val outputArea = new JTextArea()

  def addComponentsToPane(pane: Container): Unit = {
    val controls = new JPanel
    val layout = new BorderLayout()
    controls.setLayout(layout)

    outputArea.setMargin(new Insets(10, 10, 10, 10))
    outputArea.setEditable(false)
    controls.add(outputArea, BorderLayout.CENTER)

    controls.add(inputField, BorderLayout.SOUTH)
    controls.setComponentOrientation(ComponentOrientation.LEFT_TO_RIGHT)

    pane.add(controls, BorderLayout.CENTER)
  }
}
