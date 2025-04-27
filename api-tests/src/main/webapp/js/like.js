class LikeButton extends React.Component {
    constructor(props) {
        super(props);
        this.state = {liked: false};
    }

    render() {
        if (this.state.liked) {
            function tick() {
                const element = React.createElement(
                    'button',
                    {
                        id: 'but',
                    },
                    `Time, ${new Date().toLocaleTimeString()}`,
                )
                ReactDOM.render(element, document.getElementById("root"));
            }

            setInterval(tick, 1000);
        } else {
            const element = React.createElement(
                'button',
                {
                    id: 'but',
                    onClick: () => this.setState({liked: true}),
                },
                'Show time'
            );
            ReactDOM.render(element, document.getElementById("root"));
        }
    }
}

const domContainer = document.querySelector('#root');
const root = ReactDOM.createRoot(domContainer);
root.render(React.createElement(LikeButton));
