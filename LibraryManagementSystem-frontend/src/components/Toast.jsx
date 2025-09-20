import "./Toast.css"

function Toast({message,action}) {
    return <div id="toast" role="alert" aria-live="polite">
        <p>{message}</p>
        <button onClick={action}>Ok</button>
    </div>
}

export default Toast;