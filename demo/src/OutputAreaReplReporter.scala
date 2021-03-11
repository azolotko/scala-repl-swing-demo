import java.io.{PrintWriter, Writer}
import javax.swing.{JTextArea, SwingUtilities}
import scala.reflect.internal.util.Position
import scala.tools.nsc.{NewLinePrintWriter, Settings}
import scala.tools.nsc.interpreter.{ReplReporter, ReplRequest}

class OutputAreaReplReporter(outputArea: JTextArea, _settings: Settings)
    extends ReplReporter {
  override def out: PrintWriter =
    new NewLinePrintWriter(
      new Writer {
        override def write(cbuf: Array[Char], off: Int, len: Int): Unit = {
          val s = new String(cbuf, off, len)
          SwingUtilities.invokeLater(() => outputArea.append(s))
        }

        override def flush(): Unit = ()

        override def close(): Unit = ()
      },
      autoFlush = true
    )

  override def printMessage(msg: String): Unit =
    out.print(msg)

  override def suppressOutput[T](body: => T): T =
    body

  override def withoutTruncating[T](body: => T): T =
    body

  override def withoutUnwrapping(body: => Unit): Unit =
    body

  override def indenting(n: Int)(body: => Unit): Unit =
    body

  override def printResult(result: Either[String, String]): Unit =
    result.fold(out.println, out.println)

  override def withoutPrintingResults[T](body: => T): T =
    body

  override def printResults: Boolean =
    true

  override def togglePrintResults(): Unit =
    ()

  override def isDebug: Boolean =
    false

  override def isTrace: Boolean =
    false

  override var currentRequest: ReplRequest = _

  override def settings: Settings = _settings

  override def doReport(pos: Position, msg: String, severity: Severity): Unit =
    out.println(msg)

  override def nameToCode(s: String): String =
    s

  override def typeToCode(s: String): String =
    s
}
