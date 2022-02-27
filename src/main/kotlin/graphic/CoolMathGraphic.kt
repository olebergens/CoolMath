package graphic

import math.ulamspiral.UlamSpiral
import tornadofx.*


class MainApp : App(CoolMathGraphic::class)

fun main(args: Array<String>) {
    launch<MainApp>(args)
}

private const val WIDTH = 820.0
private const val HEIGHT = 820.0

class CoolMathGraphic : View("CoolMath") {

    private var ulamSpiral: UlamSpiral? = null

    override fun onDock() {
        ulamSpiral!!.run()
    }

    override val root = stackpane {
        setPrefSize(WIDTH, HEIGHT)
        ulamSpiral = UlamSpiral(WIDTH, HEIGHT, 1)
        this.group {
            this.bindChildren(ulamSpiral!!.actors) {
                circle(it.ux, it.uy, 1.0)
            }
            this.bindChildren(ulamSpiral!!.actors) {
                line(it.ux, it.uy, it.upx, it.upy)
            }
        }
    }

}