import java.awt.Dimension
import java.awt.event.ActionEvent
import javax.swing.{SwingUtilities, WindowConstants}
import scala.tools.nsc.Settings
import scala.tools.nsc.interpreter._

object App extends scala.App {
  val frame = new ReplFrame

  val settings = new Settings
  settings.processArgumentString("-usejavacp")

  val reporter = new OutputAreaReplReporter(frame.outputArea, settings)

  val interpreter = new IMain(settings, reporter)

  frame.inputField.addActionListener((_: ActionEvent) => {
    interpreter.interpret(frame.inputField.getText) match {
      case Results.Success =>
        frame.inputField.setText("")
      case Results.Error      =>
      case Results.Incomplete =>
    }
  })

  def interpret(code: String): Unit = {
    import Results._
    val res = interpreter.interpret(code) match {
      case Success => "OK!"
      case _       => "Sorry, try again."
    }
    println(res)
  }

  def show(): Unit = {
    frame.setMinimumSize(new Dimension(800, 600))
    frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE)
    frame.addComponentsToPane(frame.getContentPane)
    frame.pack()
    frame.setVisible(true)

    frame.inputField.requestFocus()
  }

  SwingUtilities.invokeLater(() => show())
}
